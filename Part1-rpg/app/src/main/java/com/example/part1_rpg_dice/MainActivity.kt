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
    var VIT by remember {mutableIntStateOf(10)}
    var DEX by remember {mutableIntStateOf(10)}
    var WIS by remember {mutableIntStateOf(10)}

    Column(
        modifier = modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        Text(
            text = "Estadisticas",
            fontSize = 12.sp,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        StatRow(
            name = "Vitalidad",
            value = VIT,
            onRoll = { VIT = (1..20).random()}
        )

        StatRow(
            name = "Destreza",
            value = DEX,
            onRoll = { DEX = (1..20).random()}
        )

        StatRow(
            name = "Sabiduría",
            value = WIS,
            onRoll = { WIS = (1..20).random()}
        )
    }
    val TOTAL = VIT + DEX + WIS
}

@Composable
fun StatRow(
    name: String,
    value: Int,
    onRoll: () -> Unit,
    modifier: Modifier = Modifier
){
    Row(
        modifier = modifier.fillMaxWidth().padding(8.dp),
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
            modifier = Modifier.weight(1f)
        ) {
            Text(text = "Girar")
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