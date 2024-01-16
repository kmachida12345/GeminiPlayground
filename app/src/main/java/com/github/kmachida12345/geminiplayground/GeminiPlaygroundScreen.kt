package com.github.kmachida12345.geminiplayground

import androidx.compose.runtime.Composable
import com.github.kmachida12345.geminiplayground.ui.summarize.SummarizeRoute

enum class GeminiPlaygroundScreen(
    val body: @Composable ((String) -> Unit) -> Unit,
) {
    Summarize(
        body = {
            SummarizeRoute()
        },
    ),
    ;

    @Composable
    fun content(onScreenChange: (String) -> Unit = {}) {
        body(onScreenChange)
    }

    companion object {
        fun fromRoute(route: String?): GeminiPlaygroundScreen = when (route?.substringBefore("/")) {
            Summarize.name -> Summarize
            null -> Summarize
            else -> throw IllegalArgumentException("Route $route is not recognized.")
        }
    }
}
