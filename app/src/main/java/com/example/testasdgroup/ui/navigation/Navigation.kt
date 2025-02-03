package com.example.testasdgroup.ui.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.testasdgroup.R
import com.example.testasdgroup.ui.flightdetails.FlightDetailsScreen
import com.example.testasdgroup.ui.flightlist.FlightListScreen
import com.example.testasdgroup.viewmodel.FlightViewModel


@Composable
fun Navigation(viewModel: FlightViewModel) {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "list") {
        // List Screen
        composable("list") {
            FlightListScreen(viewModel) { flight ->
                navController.navigate("details/${flight.flightNumber}")
            }
        }
        // Details Screen
        composable("details/{flightId}") { backStackEntry ->
            // Get the flightId from the navigation arguments
            val flightId = backStackEntry.arguments?.getString("flightId")?.toIntOrNull()

            // Observe the selected flight LiveData
            val flight by viewModel.selectedFlight.observeAsState()

            // Fetch flight data by ID once when the screen is entered
            LaunchedEffect(flightId) {
                if (flightId != null) {
                    viewModel.getFlightById(flightId)
                }
            }

            // Show the UI based on flight data
            if (flightId == null) {
                Text(stringResource(R.string.main_screen_invalid_id))
            } else {
                flight?.let {
                    FlightDetailsScreen(it)
                } ?: Text(stringResource(R.string.main_screen_loading))
            }
        }
    }
}