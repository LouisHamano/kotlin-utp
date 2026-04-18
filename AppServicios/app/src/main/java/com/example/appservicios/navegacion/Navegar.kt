package com.example.appservicios.navegacion

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.ui.NavDisplay
import com.example.appservicios.modelos.ServicioData
import com.example.appservicios.ventanas.Resumen
import com.example.appservicios.ventanas.Servicio

data object ServicioScreen
data class ResumenScreen(val data: ServicioData)

@Composable
fun Navegar() {
    val backStack = remember { mutableStateListOf<Any>(ServicioScreen) }

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = { key ->
            when (key) {
                is ServicioScreen -> NavEntry(key) {
                    Servicio(irResumen = {x -> backStack.add(ResumenScreen(x))})
                }

                is ResumenScreen -> NavEntry(key) {
                    Resumen(key.data

                    )
                }
                else -> NavEntry(Unit) { Text("Unknown route") }
            }

        }
    )
}