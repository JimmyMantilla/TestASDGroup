package com.example.testasdgroup.ui.flightdetails

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.testasdgroup.data.persistent.db.FlightEntity

@Composable
fun FlightDetailsScreen(flight: FlightEntity) {
    BoxWithConstraints {
        if (maxWidth < 600.dp) {
            FlightPortraitDetailsScreen(flight)
        } else {
            FlightLandscapeDetailsScreen(flight)
        }
    }
}