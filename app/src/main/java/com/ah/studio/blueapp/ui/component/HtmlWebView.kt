package com.ah.studio.blueapp.ui.component

import android.annotation.SuppressLint
import android.webkit.WebView
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun HtmlWebView(html: String) {
    AndroidView(
        factory = { context ->
            WebView(context).apply {
                settings.javaScriptEnabled = true
                settings.loadsImagesAutomatically = true
                loadDataWithBaseURL(null, html, "text/html", "utf-8", null)
            }
        },
        update = { view ->
            view.loadDataWithBaseURL(null, html, "text/html", "utf-8", null)
        },
        modifier = Modifier.fillMaxSize()
    )
}



