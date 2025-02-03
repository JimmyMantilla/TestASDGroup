package com.example.testasdgroup.viewmodeltest

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.testasdgroup.data.persistent.db.FlightEntity
import com.example.testasdgroup.data.repository.FlightRepository
import com.example.testasdgroup.viewmodel.FlightViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class FlightViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var repository: FlightRepository
    private lateinit var viewModel: FlightViewModel
    private val testDispatcher = StandardTestDispatcher()
    private val testScope = TestScope(testDispatcher)

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
        viewModel = FlightViewModel(
            application = mock(Application::class.java),
            repository = repository
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `test refreshFlights clears current flights and loads new ones`() = testScope.runTest {
        val flightList = listOf(
            FlightEntity(
                1,
                "Mision 1",
                "Cohete 1",
                "Tipo 1",
                "Lugar de lanzamiento 1",
                "Detalles",
                "PatchUrl",
                "2025-02-01",
                dateUtc = "",
                dateUnix = 1738587080,
                webCastUrl = ""
            ),
            FlightEntity(
                2,
                "Mision 2",
                "Cohete 2",
                "Tipo 2",
                "Lugar de lanzamiento 2",
                "Detalles",
                "PatchUrl",
                "2025-02-01",
                dateUtc = "",
                dateUnix = 1738587080,
                webCastUrl = ""
            )
        )
        whenever(repository.getFlightsPaginated(20, 0)).thenReturn(flightList)

        viewModel.refreshFlights()
        advanceUntilIdle()

        verify(repository).getFlightsPaginated(20, 0)
        assert(viewModel.isLoading.value == false)
    }

    @Test
    fun `test loadMoreFlights appends flights correctly`() = testScope.runTest {
        val initialFlights = listOf(
            FlightEntity(
                1,
                "Mision 1",
                "Cohete 1",
                "Tipo 1",
                "Lugar de lanzamiento 1",
                "Detalles",
                "PatchUrl",
                "PatchUrlLarge",
                dateUtc = "",
                dateUnix = 1738587080,
                webCastUrl = ""
            )
        )
        val additionalFlights = listOf(
            FlightEntity(
                2,
                "Mision 2",
                "Cohete 2",
                "Tipo 2",
                "Lugar de lanzamiento 2",
                "Detalles",
                "PatchUrl",
                "PatchUrlLarge",
                dateUtc = "",
                dateUnix = 1738587080,
                webCastUrl = ""
            )
        )
        whenever(repository.getFlightsPaginated(20, 0)).thenReturn(initialFlights)
        whenever(repository.getFlightsPaginated(20, 20)).thenReturn(additionalFlights)

        viewModel.loadMoreFlights()
        advanceUntilIdle()

        verify(repository).getFlightsPaginated(20, 0)

        viewModel.loadMoreFlights()
        advanceUntilIdle()

        verify(repository).getFlightsPaginated(20, 20)
        assert(viewModel.isLoading.value == false)
    }

    @Test
    fun `test getFlightById calls repository and fetches flight by id`() = testScope.runTest {
        val flight = FlightEntity(
            1,
            "Mision 1",
            "Cohete 1",
            "Tipo 1",
            "Lugar de Lanzamiento 1",
            "Detalles",
            "PatchUrl",
            "2025-02-01",
            dateUtc = "",
            dateUnix = 1738587080,
            webCastUrl = ""
        )
        whenever(repository.getFlightById(1)).thenReturn(flight)

        viewModel.getFlightById(1)
        advanceUntilIdle()

        verify(repository).getFlightById(1)
        assert(viewModel.isLoading.value == false)
    }
}