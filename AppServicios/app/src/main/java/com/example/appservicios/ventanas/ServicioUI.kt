package com.example.appservicios.ventanas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.Button
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
import com.example.appservicios.modelos.ServicioData

@Composable
fun Servicio(irResumen:(ServicioData)-> Unit) {
    var lista = listOf("Dúo-Cámaras de Seguridad y Alarmas contra Robos",
            "Trio-Cámaras de Seguridad-Alarmas contra Robos/Incendio",
            "Solo Cámaras de Seguridad",
            "Solo Alarmas contra Robos",
            "Solo Alarmas contra Incendio",
            "Solo Cercos Eléctricos")

    var posService by remember { mutableIntStateOf(-1) }
    var nomService by remember { mutableStateOf("da") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp, vertical = 40.dp)
    ) {
        Text(text = "DATOS DEL CLIENTE",
            modifier = Modifier
                .fillMaxSize(),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp)

        Text(text = "SERVICIOS",
            modifier = Modifier
                .fillMaxSize(),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp)

        Column(
            modifier = Modifier.selectableGroup()
        ) {
            lista.forEachIndexed { index, item ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxSize()
                        .selectable(
                            selected = posService==index,
                            onClick = {
                                posService = index
                                nomService = item
                            }
                        )
                ) {
                    RadioButton(
                        selected = posService == index,
                        onClick = {
                            posService = index
                            nomService = item
                        }
                    )
                    Text(text = item)
                }
            }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(15.dp, Alignment.CenterHorizontally),
            modifier = Modifier
                .fillMaxSize()
        ) {
            Button(
                onClick = {}
            ) {
                Text(text = "Procesar")
            }
            Button(
                onClick = {irResumen(
                    ServicioData("Ana", "40044477", nomService)
                )}
            ) {
                Text(text = "Resumen")
            }
        }
    }
}