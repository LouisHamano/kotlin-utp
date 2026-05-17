package com.example.appcine.navegacion

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.ui.NavDisplay
import com.example.appcine.modelos.ReservaData
import com.example.appcine.ventanas.Reserva

data object ReservaScreen
data class ResumenScreen(val datos: ReservaData)
//data class Product(val id: String)

@Composable
fun Navegar() {

    val backStack = remember { mutableStateListOf<Any>(ReservaScreen) }

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = { key ->
            when (key) {
                is ReservaScreen -> NavEntry(key) {
                    Reserva(irResumen = {backStack.add(ResumenScreen(it))})
                }

                is ResumenScreen -> NavEntry(key) {

                }

                else -> NavEntry(Unit) { Text("Unknown route") }
            }
        }
    )
}