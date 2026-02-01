package com.example.part1_rpg_dice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.part1_rpg_dice.ui.theme.Part1RPGDiceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Part1RPGDiceTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CharacterSheetScreen(modifier = Modifier.padding(innerPadding))
                    // primero se cambia aqui //
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}
@Composable
fun CharacterSheetScreen(modifier: Modifier = Modifier){
    var VIT by remember {mutableIntStateOf(10)}
    var DEX by remember {mutableIntStateOf(10)}
    var WIS by remember {mutableIntStateOf(10)}
    val TOTAL = VIT + DEX + WIS
}

@Composable
fun StatRow(
    name: String,
    value: Int,
    onRoll: () -> Unit,
    modifier: Modifier = Modifier
){

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Part1RPGDiceTheme {
        Greeting("Android")
    }
}