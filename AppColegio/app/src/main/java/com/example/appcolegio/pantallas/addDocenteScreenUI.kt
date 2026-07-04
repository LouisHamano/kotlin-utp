package com.example.appcolegio.pantallas

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdicionarDocente(onBack:()-> Unit) {
    // Obtener el dao
    // var dao = db.docenteDao()
    // Crear objeto en una corrutina
    val scope = rememberCoroutineScope()
    var nombres by remember { mutableStateOf("") }
    var apellidos by remember { mutableStateOf("") }
    val sexos = listOf("Masculino", "Femenino", "Otros")
    var expanded by remember { mutableStateOf(false) }
    var nomSexo by remember { mutableStateOf("") }
    var sueldo by remember { mutableStateOf("") }
    var hijos by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Registrar Docente") },
                navigationIcon = {
                    IconButton(
                        onClick = {onBack()}
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            null
                        )
                    }
                }
            )
        }
    ) { espacio ->
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
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = apellidos,
                onValueChange = {apellidos=it},
                label = {Text("Ingresar apellidos")},
                modifier = Modifier.fillMaxWidth()
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
                label = {Text("Ingresar apellidos")},
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = hijos,
                onValueChange = {hijos=it},
                label = {Text("Ingresar apellidos")},
                modifier = Modifier.fillMaxWidth()
            )

            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    onClick = {
//                        scope.launch {
//
//                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(5.dp)
                ) {
                    Text("Grabar")
                }
            }
        }

    }
}