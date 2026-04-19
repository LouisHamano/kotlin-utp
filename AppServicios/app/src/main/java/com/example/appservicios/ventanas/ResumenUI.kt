package com.example.appservicios.ventanas

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appservicios.modelos.ServicioData

@Composable
fun Resumen(info: ServicioData, onBack: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp, vertical = 40.dp)
    ) {
        Text(
            text = "RESUMEN DEL SERVICIO",
            modifier = Modifier
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(15.dp,
                Alignment.CenterHorizontally),
            modifier = Modifier
        ) { }

        Text(text = "Cliente : ${info.cliente}")
        Text(text = "Servicio : ${info.servicio}")
        Text(text = "DNI : ${info.dni}")
        Text(text = "Costo Servicios : ${info.costo_servicio}")
        Text(text = "Costo Instalación : ${info.costo_instalacion}")
        Text(text = "Descuento : ${info.descuento}")
        Text(text = "Total Pagar : ${info.total}")

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                modifier = Modifier.padding(top = 100.dp),
                shape = RoundedCornerShape(size = 0.dp),
                onClick = {
                    onBack()
                }
            ) {
                Text(text = "Volver")
            }
        }


    }
}