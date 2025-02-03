package com.example.testasdgroup.ui.flightdetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.testasdgroup.R
import com.example.testasdgroup.data.persistent.db.FlightEntity
import com.example.testasdgroup.util.extractLaunchInfo

@Composable
fun FlightPortraitDetailsScreen(flight: FlightEntity) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Large patch image at the top
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(350.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .border(2.dp, Color.Gray, RoundedCornerShape(8.dp)),
                painter = rememberAsyncImagePainter(
                    model = flight.patchUrlLarge,
                    error = painterResource(id = R.drawable.img_placeholder),
                    placeholder = painterResource(id = R.drawable.img_placeholder)
                ),
                contentDescription = "Rocket Name",
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Rocket Name
            Text(
                text = "Rocket Name: ${flight.rocketName}",
                style = MaterialTheme.typography.titleLarge,
                color = Color.DarkGray
            )

            Spacer(modifier = Modifier.height(8.dp))
            HorizontalDivider(color = Color.Gray, thickness = 1.dp)
            Spacer(modifier = Modifier.height(8.dp))

            // Mission Name
            Text(
                text = "Mission Name: ${flight.missionName}",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.DarkGray
            )

            Spacer(modifier = Modifier.height(8.dp))
            HorizontalDivider(color = Color.Gray, thickness = 1.dp)
            Spacer(modifier = Modifier.height(8.dp))

            // Flight Number
            Text(
                text = "Flight Number: ${flight.flightNumber}",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(8.dp))
            HorizontalDivider(color = Color.Gray, thickness = 1.dp)
            Spacer(modifier = Modifier.height(8.dp))

            // Rocket Type
            Text(
                text = "Rocket Type: ${flight.rocketType}",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.DarkGray
            )

            Spacer(modifier = Modifier.height(8.dp))
            HorizontalDivider(color = Color.Gray, thickness = 1.dp)
            Spacer(modifier = Modifier.height(8.dp))

            // Launch Site Name
            Text(
                text = "Launch Site: ${flight.details.extractLaunchInfo()}",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.DarkGray
            )

            Spacer(modifier = Modifier.height(8.dp))
            HorizontalDivider(color = Color.Gray, thickness = 1.dp)
            Spacer(modifier = Modifier.height(8.dp))

            // Flight Details
            Text(
                text = "Flight Details: ${flight.details}",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.DarkGray,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))
            HorizontalDivider(color = Color.Gray, thickness = 1.dp)
            Spacer(modifier = Modifier.height(16.dp))

            YouTubeVideoScreen(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(500.dp),
                videoId = flight.webCastUrl)
        }
    }
}