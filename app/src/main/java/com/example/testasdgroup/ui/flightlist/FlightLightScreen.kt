package com.example.testasdgroup.ui.flightlist

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.testasdgroup.R
import com.example.testasdgroup.data.persistent.db.FlightEntity
import com.example.testasdgroup.viewmodel.FlightViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.filter

@Composable
fun FlightListScreen(viewModel: FlightViewModel, onFlightClick: (FlightEntity) -> Unit) {
    LaunchedEffect(Unit) {
        viewModel.refreshFlights()
    }
    val flights by viewModel.flights.observeAsState(emptyList())
    val isLoading by viewModel.isLoading.observeAsState(false)
    val listState = rememberLazyListState()

    // Gives the user a pagination effect since we already stored all the data in the database.
    LaunchedEffect(listState) {
        snapshotFlow { listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index }
            .filter { it == flights.size - 1 && !isLoading }
            .collect {
                viewModel.setLoading(true)
                delay(1500)
                viewModel.loadMoreFlights()
            }
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.universe_background),
            contentScale = ContentScale.Crop,
            contentDescription = null,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .fillMaxWidth(),
                text = stringResource(R.string.main_screen_title),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.displaySmall,
                color = Color.White,
            )
            LazyColumn(
                state = listState,
                modifier = Modifier.fillMaxSize()
            ) {
                items(flights) { flight ->
                    FlightItem(flight = flight, modifier = Modifier.clickable { onFlightClick(flight) })
                }
                if (isLoading) {
                    item {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                                .wrapContentSize(Alignment.Center)
                        ) {
                            CircularProgressIndicator()
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = stringResource(R.string.main_screen_loading_message),
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color.White
                            )
                        }
                    }
                }
            }
        }
    }
}