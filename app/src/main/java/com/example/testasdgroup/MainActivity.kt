package com.example.testasdgroup

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.testasdgroup.ui.navigation.Navigation
import com.example.testasdgroup.viewmodel.FlightViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: FlightViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen().apply {
            setOnExitAnimationListener { splashScreenViewProvider ->
                splashScreenViewProvider.view
                    .animate()
                    .alpha(0f)
                    .setDuration(500L)
                    .withEndAction { splashScreenViewProvider.remove() }
                    .start()
            }
        }

        setContent {
            Navigation(viewModel)
        }
    }
}