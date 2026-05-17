package com.example.appcine.ventanas

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Reserva(irResumen:()-> Unit){
    var mostrar by remember { mutableStateOf(false) }
    var posPelicula by remember { mutableIntStateOf(-1) }
    var nomPelicula by remember { mutableStateOf("[Seleccione Pelicula]") }
    var posGenero by remember { mutableIntStateOf(-1) }
    var nomGenero by remember { mutableStateOf("") }

    val lista=listOf("Comedia","Dramática","Terror","Infantil")

    val peliculas=when(posGenero){
        0-> listOf("Super cool","¿Qué pasó ayer?")
        1-> listOf("Lo imposible","12 años de esclavitud","Historias cruzadas")
        2-> listOf("Annabellen","Nosotros","Un lugar en silencio","Halloween")
        3-> listOf("Alvin y las ardillas","Arthur y los Minimoys","Bolt","Cars","Encantada")
        else -> emptyList()
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp, vertical = 40.dp)
    ) {
        Text(
            text = "RESERVA",
            modifier = Modifier
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )

        Column(
            modifier = Modifier.selectableGroup()
        ){
            lista.forEachIndexed { index, item ->
                Row(
                    modifier = Modifier
                        .selectable(
                            selected = posGenero == index,
                            onClick = {
                                posGenero = index
                                nomGenero = item
                                nomPelicula="[Seleccione Pelicula]"
                            },
                            role = Role.RadioButton
                        )
                        .height(35.dp)
                        .fillMaxWidth(),

                    verticalAlignment = Alignment.CenterVertically
                ){
                    RadioButton(
                        selected = posGenero==index,
                        onClick = null
                    )
                    Text(text = item)

                }
            }
        }

        Column(){

            Button(
                onClick = { mostrar = true },
                shape = RoundedCornerShape(0.dp),
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(text = nomPelicula,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Start)
            }
            DropdownMenu(
                expanded = mostrar,
                onDismissRequest = { mostrar = false }
            ) {
                peliculas.forEachIndexed { index, valor ->
                    DropdownMenuItem(
                        text = { Text(valor) },
                        onClick = {
                            mostrar = false
                            posPelicula=index
                            nomPelicula=valor
                        }
                    )
                }

            }
        }






    }
}
