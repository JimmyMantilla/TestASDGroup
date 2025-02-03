package com.example.testasdgroup.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.testasdgroup.data.persistent.db.FlightEntity
import com.example.testasdgroup.data.repository.FlightRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FlightViewModel @Inject constructor(
    application: Application,
    private val repository: FlightRepository
) : AndroidViewModel(application) {

    // LiveData to observe flights from Room
    private val _flights = MutableLiveData<List<FlightEntity>>(emptyList())
    val flights: LiveData<List<FlightEntity>> = _flights

    // LiveData to observe selected flight details
    private val _selectedFlight = MutableLiveData<FlightEntity?>()
    val selectedFlight: LiveData<FlightEntity?> = _selectedFlight

    // LiveData for loading state
    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    // Offset for pagination
    private var currentOffset = 0

    fun setLoading(loading: Boolean) {
        _isLoading.value = loading
    }

    fun refreshFlights() {
        viewModelScope.launch {
            currentOffset = 0
            _flights.value = emptyList()
            loadMoreFlights()
        }
    }

    // Load more flights (pagination)
    fun loadMoreFlights() {
        viewModelScope.launch {
            try {
                // Fetch the next batch of flights from the repository (20 items per batch)
                val newFlights = repository.getFlightsPaginated(limit = 20, offset = currentOffset)

                if (newFlights.isNotEmpty()) {
                    val currentList = _flights.value.orEmpty()
                    _flights.value = currentList + newFlights
                    currentOffset += 20
                } else {
                    Log.d("FlightViewModel", "No hay mas vuelos disponibles para cargar")
                }
            } catch (payloadException: Exception) {
                Log.e("FlightViewModel", "Error al cargar mas vuelos", payloadException)
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun getFlightById(flightId: Int) {
        viewModelScope.launch {
            try {
                _selectedFlight.value = repository.getFlightById(flightId)
            } catch (payloadException: Exception) {
                Log.e("FlightViewModel", "Error fetching flight by ID", payloadException)
            }
        }
    }

    fun clearSelectedFlight() {
        _selectedFlight.value = null
    }
}