package com.example.testasdgroup.data.persistent.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FlightDao {
    @Query("SELECT * FROM flights ORDER BY flightNumber DESC LIMIT :limit OFFSET :offset")
    suspend fun getFlightsPaginated(limit: Int, offset: Int): List<FlightEntity>

    @Query("SELECT * FROM flights WHERE flightNumber = :flightId LIMIT 1")
    suspend fun getFlightById(flightId: Int): FlightEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFlights(flights: List<FlightEntity>)
}