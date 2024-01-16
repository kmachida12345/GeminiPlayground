package com.github.kmachida12345.geminiplayground

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.github.kmachida12345.geminiplayground.ui.theme.GeminiPlaygroundTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GeminiPlaygroundApp()
        }
    }
}

@Composable
fun GeminiPlaygroundApp() {
    GeminiPlaygroundTheme {
        val navController = rememberNavController()

        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background,
        ) {
            NavHost(
                navController = navController,
                startDestination = GeminiPlaygroundScreen.Main.name,
            ) {
                composable(GeminiPlaygroundScreen.Summarize.name) {
                    GeminiPlaygroundScreen.Summarize.content()
                }

                composable(GeminiPlaygroundScreen.Main.name) {
                    GeminiPlaygroundScreen.Main.content(
                        onScreenChange = { screen ->
                            navController.navigate(screen)
                        },
                    )
                }

                composable(GeminiPlaygroundScreen.MultiModal.name) {
                    GeminiPlaygroundScreen.MultiModal.content()
                }
            }
        }
    }
}
