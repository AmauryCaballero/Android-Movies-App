package com.larrykapija.moviesapp.ui.screens.details.components

import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.larrykapija.moviesapp.network.response.Video

@Composable
fun YouTubeWebView(video: Video) {
    val context = LocalContext.current

    AndroidView(
        factory = { ctx ->
            WebView(ctx).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                webViewClient = WebViewClient()
                settings.javaScriptEnabled = true
                settings.domStorageEnabled = true
                loadUrl("https://www.youtube.com/watch?v=${video.key}")
            }
        },
        update = { webView ->
            webView.loadUrl("https://www.youtube.com/watch?v=${video.key}")
        }
    )
}
