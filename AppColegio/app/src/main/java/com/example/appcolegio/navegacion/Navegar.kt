package com.example.appcolegio.navegacion
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.ui.NavDisplay
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.room.Room
import com.example.appcolegio.pantallas.AdicionarDocente
import com.example.appcolegio.pantallas.ListaDocente

data object ListDocente
data object AddDocente

@Composable
fun Navegar() {

    val backStack = remember { mutableStateListOf<Any>(ListDocente) }
    val context = LocalContext.current
    // Crear DB
    val db = remember {
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "colegio.db"
        ).build()
    }

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = { key ->
            when (key) {
                is ListDocente -> NavEntry(key) {
                    ListaDocente(addDocente = {backStack.add(AddDocente)})
                }

                is AddDocente -> NavEntry(key) {
                    AdicionarDocente(onBack = {backStack.removeLastOrNull()})
                }

                else -> NavEntry(Unit) { Text("Unknown route") }
            }
        }
    )
}