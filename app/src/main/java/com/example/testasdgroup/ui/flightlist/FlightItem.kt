package com.example.testasdgroup.ui.flightlist

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.testasdgroup.R
import com.example.testasdgroup.data.persistent.db.FlightEntity
import com.example.testasdgroup.util.toFormattedDate

@Composable
fun FlightItem(
    flight: FlightEntity,
    modifier: Modifier = Modifier
) {
    val date = flight.dateUnix.toFormattedDate()
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Image(
                modifier = Modifier.size(64.dp),
                painter = rememberAsyncImagePainter(
                    model = flight.patchUrl,
                    error = painterResource(id = R.drawable.img_placeholder),
                    placeholder = painterResource(id = R.drawable.img_placeholder)
                ),
                contentDescription = null,
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(
                    text = flight.rocketName,
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Launch Date: $date",
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = "click for more detail",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}