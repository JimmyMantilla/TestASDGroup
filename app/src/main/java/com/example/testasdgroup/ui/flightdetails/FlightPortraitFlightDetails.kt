package com.example.testasdgroup.ui.flightdetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.testasdgroup.R
import com.example.testasdgroup.data.persistent.db.FlightEntity
import com.example.testasdgroup.util.extractLaunchInfo

@Composable
fun FlightPortraitDetailsScreen(flight: FlightEntity) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center // Center the Card inside the Box
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center // Align content vertically centered
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
                    text = "Nombre de cohete: ${flight.rocketName}",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = Color.DarkGray
                )

                Spacer(modifier = Modifier.height(8.dp))
                HorizontalDivider(color = Color.Gray, thickness = 1.dp)
                Spacer(modifier = Modifier.height(8.dp))

                // Mission Name
                Text(
                    text = "Nombre de la mision: ${flight.missionName}",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.DarkGray
                )

                Spacer(modifier = Modifier.height(8.dp))
                HorizontalDivider(color = Color.Gray, thickness = 1.dp)
                Spacer(modifier = Modifier.height(8.dp))

                // Flight Number
                Text(
                    text = "Numero de vuelo: ${flight.flightNumber}",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(8.dp))
                HorizontalDivider(color = Color.Gray, thickness = 1.dp)
                Spacer(modifier = Modifier.height(8.dp))

                // Rocket Type
                Text(
                    text = "Tipo de cohete: ${flight.rocketType}",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.DarkGray
                )

                Spacer(modifier = Modifier.height(8.dp))
                HorizontalDivider(color = Color.Gray, thickness = 1.dp)
                Spacer(modifier = Modifier.height(8.dp))

                // Launch Site Name
                Text(
                    text = "Lugar de lanzamiento: ${flight.details.extractLaunchInfo()}",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.DarkGray
                )

                Spacer(modifier = Modifier.height(8.dp))
                HorizontalDivider(color = Color.Gray, thickness = 1.dp)
                Spacer(modifier = Modifier.height(8.dp))

                // Flight Details
                Text(
                    text = "Detalles de vuelo: ${flight.details}",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.DarkGray,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(8.dp))
                HorizontalDivider(color = Color.Gray, thickness = 1.dp)
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}