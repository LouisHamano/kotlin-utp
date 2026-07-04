package com.example.appcolegio.navegacion

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.ui.NavDisplay
import androidx.room.Room
import com.example.appcolegio.pantallas.AdicionarMenu
import com.example.appcolegio.pantallas.EditarMenu
import com.example.appcolegio.pantallas.ListaMenu
data object ListMenu
data object AddMenu
data class EditMenu(val cod:Int)

@Composable
fun Navegar() {

    val backStack = remember { mutableStateListOf<Any>(ListMenu) }

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = { key ->
            when (key) {
                is ListMenu -> NavEntry(key) {
                    ListaMenu(addMenu = {backStack.add(AddMenu)},
                        editMenu = {backStack.add(EditMenu(it))})
                }
                is AddMenu -> NavEntry(key) {
                    AdicionarMenu(onBack = {backStack.removeLastOrNull()})
                }
                is EditMenu -> NavEntry(key) {
                    EditarMenu(onBack = {backStack.removeLastOrNull()},
                        codigo = key.cod)
                }
                else -> NavEntry(Unit) { Text("Unknown route") }
            }
        }
    )
}