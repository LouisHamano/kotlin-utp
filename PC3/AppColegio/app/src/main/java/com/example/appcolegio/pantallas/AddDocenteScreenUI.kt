package com.example.appcolegio.pantallas

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.appcolegio.local.AppDatabase
import com.example.appcolegio.local.entidades.Docente
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdicionarDocente(onBack:()->Unit,db: AppDatabase){
    //obtener el dao
    val dao=db.docenteDao()
    //crear objeto de una corrutina
    val scope= rememberCoroutineScope()
    //snackbar
    val snackbar=remember { SnackbarHostState() }

    var nombres by remember { mutableStateOf("") }
    var apellidos by remember { mutableStateOf("") }
    var sueldo by remember { mutableStateOf("") }
    var hijos by remember { mutableStateOf("") }
    val sexos = listOf("Masculino", "Femenino", "Otros")
    var expanded by remember { mutableStateOf(false) }
    var nomSexo by remember { mutableStateOf("") }

    Scaffold(
        snackbarHost = {
            SnackbarHost(snackbar)
        },
        topBar = {
            TopAppBar(
                title = { Text("Registrar Docente") },
                navigationIcon = {
                    IconButton(
                        onClick = {onBack()}
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ){ espacio->
        Column(
            modifier = Modifier
                .padding(espacio)
                .padding(15.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            OutlinedTextField(
                value = nombres,
                onValueChange = {nombres=it},
                label = {Text("Ingresar nombres")},
                modifier = Modifier
                    .fillMaxWidth()
            )
            OutlinedTextField(
                value = apellidos,
                onValueChange = {apellidos=it},
                label = {Text("Ingresar apellidos")},
                modifier = Modifier
                    .fillMaxWidth()
            )
            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded }
            ) {

                OutlinedTextField(
                    value = nomSexo,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("[Seleccione Sexo]") },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .menuAnchor(ExposedDropdownMenuAnchorType.PrimaryNotEditable)
                )
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    sexos.forEach { item ->
                        DropdownMenuItem(
                            text = { Text(item) },
                            onClick = {
                                nomSexo = item
                                expanded = false
                            }
                        )
                    }
                }
            }
            OutlinedTextField(
                value = sueldo,
                onValueChange = {sueldo=it},
                label = {Text("Ingresar sueldo")},
                modifier = Modifier
                    .fillMaxWidth()
            )
            OutlinedTextField(
                value = hijos,
                onValueChange = {hijos=it},
                label = {Text("Ingresar hijos")},
                modifier = Modifier
                    .fillMaxWidth()
            )
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Bottom
            ){
                Button(
                    onClick = {
                        scope.launch {
                            try {
                                dao.registrar(
                                    Docente(0,nombres,apellidos,
                                        nomSexo,sueldo.toDouble(),hijos.toInt())
                                )
                                snackbar.showSnackbar("Docente registrado")
                            }
                            catch (e: Exception){
                                snackbar.showSnackbar("Error : ${e.message}")
                            }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(5.dp)
                ){
                    Text("Grabar")
                }
            }

        }

    }


}