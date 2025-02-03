package com.example.testasdgroup.data.repository

import android.util.Log
import com.example.testasdgroup.data.networking.service.SpaceXApiService
import com.example.testasdgroup.data.persistent.db.FlightDao
import com.example.testasdgroup.data.persistent.db.FlightEntity
import com.example.testasdgroup.util.toFlightEntity
import javax.inject.Inject

class FlightRepository @Inject constructor(
    private val apiService: SpaceXApiService,
    private val flightDao: FlightDao
) {

    suspend fun getFlightsPaginated(limit: Int, offset: Int): List<FlightEntity> {
        return try {
            val flightsInDb = flightDao.getFlightsPaginated(limit, offset)

            // Fetch data from the API and store in Room
            if (flightsInDb.isEmpty() && offset == 0) {
                fetchAndStoreFlights()
                flightDao.getFlightsPaginated(limit, offset)
            } else {
                flightDao.getFlightsPaginated(limit, offset)
            }
        } catch (daoError: Exception) {
            Log.e("FlightRepository", "Error fetching flights from database", daoError)
            emptyList()
        }
    }

    private suspend fun fetchAndStoreFlights() {
        try {
            // Fetch flights from the API
            val response = apiService.getLatestFlights()

            // Map FlightDto to FlightEntity for storing in Room
            val flightEntities = response.map { flightDto ->
                flightDto.toFlightEntity()
            }.sortedByDescending { flightEntity ->
                flightEntity.dateUnix
            }

            // Insert or update the flights in the Room database
            flightDao.insertFlights(flightEntities)
        } catch (apiError: Exception) {
            Log.e("FlightRepository", "Error fetching flights from API", apiError)
        }
    }

    suspend fun getFlightById(flightId: Int): FlightEntity? {
        return try {
            flightDao.getFlightById(flightId)
        } catch (daoError: Exception) {
            Log.e("FlightRepository", "Error fetching flight by ID from database", daoError)
            null
        }
    }
}