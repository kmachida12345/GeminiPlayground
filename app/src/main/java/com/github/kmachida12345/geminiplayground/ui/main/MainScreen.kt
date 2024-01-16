package com.github.kmachida12345.geminiplayground.ui.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.kmachida12345.geminiplayground.GeminiPlaygroundScreen

@Composable
fun MainScreen(onScreenChange: (String) -> Unit = {}) {
    Column(
        modifier =
            Modifier
                .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
    ) {
        Button(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
            onClick = {
                onScreenChange(GeminiPlaygroundScreen.Summarize.name)
            },
        ) {
            Text(text = GeminiPlaygroundScreen.Summarize.name)
        }
        Button(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
            onClick = {
                onScreenChange(GeminiPlaygroundScreen.MultiModal.name)
            },
        ) {
            Text(text = GeminiPlaygroundScreen.MultiModal.name)
        }

    }
}

@Preview
@Composable
private fun MainScreenPreview() {
    MainScreen()
}
