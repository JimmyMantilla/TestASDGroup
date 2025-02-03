package com.example.testasdgroup.ui.flightdetails

import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun YouTubeVideoScreen(videoId: String, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val videoFallbackUrl = "https://www.youtube.com/watch?v=dLQ2tZEH6G0"
    AndroidView(
        modifier = modifier
            .fillMaxWidth()
            .height(350.dp),
        factory = {
            WebView(context).apply {
                webViewClient = WebViewClient()
                webChromeClient = WebChromeClient()
                settings.javaScriptEnabled = true

                if (videoId.isNotEmpty()) {
                    loadUrl(videoId)
                } else {
                    loadUrl(videoFallbackUrl)
                }
            }
        }
    )
}