package com.example.appcolegio.navegacion

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.ui.NavDisplay
import androidx.room.Room
import com.example.appcolegio.local.AppDatabase
import com.example.appcolegio.pantallas.AdicionarDocente
import com.example.appcolegio.pantallas.AdicionarMenu
import com.example.appcolegio.pantallas.EditarDocente
import com.example.appcolegio.pantallas.EditarMenu
import com.example.appcolegio.pantallas.ListaDocente
import com.example.appcolegio.pantallas.ListaMenu

//data object ListDocente
//data object AddDocente

data class EditDocente(val cod:Int)

data object ListMenu
data object AddMenu
data class EditMenu(val cod:Int)

@Composable
fun Navegar() {

    val backStack = remember { mutableStateListOf<Any>(ListMenu) }
    val context= LocalContext.current
    /*crear base de datos*/
    val db=remember {
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
//                is ListDocente -> NavEntry(key) {
//                    ListaDocente(addDocente = {backStack.add(AddDocente)},db,
//                        datosDocente = {  backStack.add(EditDocente(it))}
//                        )
//                }
//                is AddDocente -> NavEntry(key) {
//                    AdicionarDocente(onBack = {backStack.removeLastOrNull()},db)
//                }
//                is EditDocente -> NavEntry(key) {
//                    EditarDocente(onBack = {backStack.removeLastOrNull()},db,
//                        codigo = key.cod)
//                }
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