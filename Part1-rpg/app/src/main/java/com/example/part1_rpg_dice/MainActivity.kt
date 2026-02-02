package com.example.part1_rpg_dice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.part1_rpg_dice.ui.theme.Part1RPGDiceTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import androidx.compose.runtime.mutableStateOf

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Part1RPGDiceTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CharacterSheetScreen(modifier = Modifier.padding(innerPadding))
                    // primero se cambia aqui para que el create cree nuestra UI con sus composes en vez de XML
                }
            }
        }
    }
}

@Composable
fun CharacterSheetScreen(modifier: Modifier = Modifier){
    // Todos los composes llevan siempre un modifier para controlar visualmente el codigo
    var vit by remember {mutableIntStateOf(10)}
    var dex by remember {mutableIntStateOf(10)}
    var wis by remember {mutableIntStateOf(10)}
    val total = vit + dex + wis


    var vitRolling by remember { mutableStateOf(false) }
    var dexRolling by remember { mutableStateOf(false) }
    var wisRolling by remember { mutableStateOf(false) }
    val anyRolling = vitRolling || dexRolling || wisRolling

    val scope = rememberCoroutineScope()

    fun rollAnimated(
        setRolling: (Boolean) -> Unit,
        setValue: (Int) -> Unit
    ) {
        scope.launch {
            setRolling(true)

            repeat(15) {
                setValue((1..20).random())
                delay(80)
            }

            setRolling(false)
        }
    }

    Column(
        modifier = modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Estadisticas",
            fontSize = 12.sp,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        StatRow(
            name = "Vitality",
            value = vit,
            isRolling = vitRolling,
            onRoll = {
                rollAnimated(
                    setRolling = { vitRolling = it },
                    setValue = { vit = it }
                )
            }
        )

        StatRow(
            name = "Dexterity",
            value = dex,
            isRolling = dexRolling,
            onRoll = {
                rollAnimated(
                    setRolling = { dexRolling = it },
                    setValue = { dex = it }
                )
            }
        )

        StatRow(
            name = "Wisdom",
            value = wis,
            isRolling = wisRolling,
            onRoll = {
                rollAnimated(
                    setRolling = { wisRolling = it },
                    setValue = { wis = it }
                )
            }
        )
    }
    Text(
        text = "Total: $total",
        fontSize = 22.sp,
        modifier = Modifier.padding(top = 16.dp)
    )
    val message = when {
        total < 30 -> "Re-roll recomendado"
        total >= 50 -> "Godlike!"
        else -> ""
    }

    val messageColor = when {
        total < 30 -> androidx.compose.ui.graphics.Color.Red
        total >= 50 -> androidx.compose.ui.graphics.Color(0xFFFFD700) // gold
        else -> androidx.compose.ui.graphics.Color.Transparent
    }

    if (!anyRolling && message.isNotEmpty()) {
        Text(
            text = message,
            color = messageColor,
            fontSize = 18.sp
        )
    }
}

@Composable
fun StatRow(
    name: String,
    value: Int,
    isRolling: Boolean,
    onRoll: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = name,
                modifier = Modifier.weight(1f),
                fontSize = 18.sp
            )

            Text(
                text = value.toString(),
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center,
                fontSize = 18.sp
            )

            Button(
                onClick = onRoll,
                enabled = !isRolling,
                modifier = Modifier.weight(1f)
            ) {
                Text(if (isRolling) "Rolling..." else "Roll")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CharacterSheetPreview() {
    Part1RPGDiceTheme {
        CharacterSheetScreen()
    }
}