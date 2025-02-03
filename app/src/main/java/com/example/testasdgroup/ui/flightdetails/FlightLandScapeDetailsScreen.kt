package com.example.testasdgroup.ui.flightdetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.testasdgroup.R
import com.example.testasdgroup.data.persistent.db.FlightEntity
import com.example.testasdgroup.util.extractLaunchInfo

@Composable
fun FlightLandscapeDetailsScreen(flight: FlightEntity) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center, // Center vertically
        horizontalAlignment = Alignment.CenterHorizontally // Center horizontally
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.Top  // Align text to the top of the image
            ) {
                // Image on the left side
                Image(
                    painter = rememberAsyncImagePainter(
                        model = flight.patchUrlLarge,
                        error = painterResource(id = R.drawable.img_placeholder),
                        placeholder = painterResource(id = R.drawable.img_placeholder)
                    ),
                    contentDescription = "Rocket patch",
                    modifier = Modifier
                        .size(150.dp)  // Fixed size for image
                        .clip(RoundedCornerShape(8.dp))
                        .border(2.dp, Color.Gray, RoundedCornerShape(8.dp))
                        .align(Alignment.CenterVertically)
                )

                Spacer(modifier = Modifier.width(16.dp))  // Space between image and text

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)  // Add space between text items
                ) {
                    // Flight Number
                    Text(
                        text = "Numero de vuelo: ${flight.flightNumber}",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )

                    HorizontalDivider(color = Color.Gray, thickness = 1.dp)

                    // Mission Name
                    Text(
                        text = "Nombre de la mision: ${flight.missionName}",
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.DarkGray
                    )

                    HorizontalDivider(color = Color.Gray, thickness = 1.dp)

                    // Rocket Name
                    Text(
                        text = "Nombre del cohete: ${flight.rocketName}",
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.DarkGray
                    )

                    HorizontalDivider(color = Color.Gray, thickness = 1.dp)

                    // Rocket Type
                    Text(
                        text = "Tipo de cohete: ${flight.rocketType}",
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.DarkGray
                    )

                    HorizontalDivider(color = Color.Gray, thickness = 1.dp)

                    // Launch Site
                    Text(
                        text = "Lugar de lanzamiento: ${flight.details.extractLaunchInfo()}",
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.DarkGray
                    )

                    HorizontalDivider(color = Color.Gray, thickness = 1.dp)

                    // Flight Details
                    Text(
                        text = "Detalles de lanzamiento: ${flight.details}",
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.DarkGray,
                        textAlign = TextAlign.Start
                    )
                }
            }
        }
    }
}
