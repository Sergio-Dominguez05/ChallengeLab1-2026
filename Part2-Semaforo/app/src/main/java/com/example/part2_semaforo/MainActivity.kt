package com.example.part2_semaforo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

enum class Light {
    Red, Yellow, Green
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            MaterialTheme {
                TrafficLightScreen()
            }
        }
    }
}

@Composable
fun TrafficLightScreen() {

    var state by remember { mutableStateOf(Light.Red) }

    LaunchedEffect(Unit) {

        while (true) {

            state = Light.Red
            delay(2000)

            state = Light.Green
            delay(2000)

            state = Light.Yellow
            delay(1000)
        }
    }


    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Carcasa negra del semáforo
        Box(
            modifier = Modifier
                .background(
                    color = Color.Black,
                    shape = androidx.compose.foundation.shape.RoundedCornerShape(24.dp)
                )
                .padding(24.dp)
        ) {

            // Luces internas
            Column(
                verticalArrangement = Arrangement.spacedBy(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                LightCircle(
                    isActive = state == Light.Red,
                    activeColor = Color.Red
                )

                LightCircle(
                    isActive = state == Light.Yellow,
                    activeColor = Color.Yellow
                )

                LightCircle(
                    isActive = state == Light.Green,
                    activeColor = Color.Green
                )
            }
        }
    }
}

@Composable
fun LightCircle(
    isActive: Boolean,
    activeColor: Color
) {

    val color = if (isActive) activeColor else Color.Gray

    Box(
        modifier = Modifier
            .size(120.dp)
            .background(color, CircleShape)
    )
}