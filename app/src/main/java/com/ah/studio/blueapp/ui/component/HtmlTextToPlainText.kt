package com.ah.studio.blueapp.ui.component

import androidx.compose.runtime.Composable
import androidx.core.text.HtmlCompat

@Composable
fun htmlTextToPlainText(htmlString: String) =
    HtmlCompat.fromHtml(htmlString, HtmlCompat.FROM_HTML_MODE_LEGACY).toString()
