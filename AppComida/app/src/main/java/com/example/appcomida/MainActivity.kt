package com.example.appcomida

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appcomida.ui.theme.AppComidaTheme
import kotlin.math.pow

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            // ventana()
            caso2()
        }
    }
}

@Composable
fun caso2() {
    var cuota_mensual by remember { mutableStateOf("79.17") }
    var tasa_anual by remember { mutableStateOf("40.76") }
    var tiempo by remember { mutableStateOf("18") }

    var precio_real by remember { mutableDoubleStateOf(0.0) }
    var total_pagar by remember { mutableDoubleStateOf(0.0) }
    var interes by remember { mutableDoubleStateOf(0.0) }

    var tasa_mensual by remember { mutableDoubleStateOf(0.0) }

    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
            .padding(top = 50.dp, start = 15.dp, end = 15.dp)
    ) {
        Text(text = "Cuota Mensual")
        TextField(
            value = cuota_mensual,
            onValueChange = {bean->cuota_mensual=bean},
            modifier = Modifier
                .fillMaxWidth()
        )

        Text(text = "Tasa Anual")
        TextField(
            value = tasa_anual,
            onValueChange = {bean->tasa_anual=bean},
            modifier = Modifier
                .fillMaxWidth()
        )

        Text(text = "Tiempo")
        TextField(
            value = tiempo,
            onValueChange = {bean->tiempo=bean},
            modifier = Modifier
                .fillMaxWidth()
        )

        Text(text = "Precio Real: S/. ${precio_real}")
        Text(text = "Total Pagar: S/. ${total_pagar}")
        Text(text = "Interés: S/. ${interes}")

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 100.dp),
            shape = RoundedCornerShape(size = 0.dp),
            onClick = {
                tasa_mensual = tasa_anual.toDouble() / 12

                precio_real = cuota_mensual.toDouble() * ((1 - (1 + tasa_mensual.pow(-tiempo.toInt())) / tasa_mensual))
                // total_pagar = precio_real * (1 + (tasa_anual.toDouble() * 0.1 ) * tiempo.toDouble())

            }
        ) {
            Text(text = "CALCULAR")
        }
    }
}

@Composable
fun ventana(){

    //variables reactivas
    var mostrar by remember { mutableStateOf(false) }
    var item by remember { mutableStateOf("Ninguno") }
    var cantidad by remember { mutableStateOf("0") }
    var precio by remember { mutableDoubleStateOf(0.0) }
    var total by remember { mutableDoubleStateOf(0.0) }
    var delivery by remember { mutableStateOf(false) }
    var descuento by remember { mutableDoubleStateOf(0.0) }
    var pagar by remember { mutableDoubleStateOf(0.0) }
    var posicion by remember { mutableStateOf(-1) }

    //variable para almacenas productos
    var lista=listOf("Ninguno","1 Pollo a la brasa","½ Pollo a la brasa",
        "¼ Pollo a la brasa","Porción de anticucho","Pechuga de pollo")

    Column(
        modifier = Modifier
            .background(Color.Cyan)
            .fillMaxSize()
            .padding(top = 50.dp, start = 15.dp, end = 15.dp)
    ){
        Text(
            text = "POLLERIA SUPER POLLO",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
        )
        Text(
            text = "Producto",
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(top = 10.dp)
        )
        Column() {

            Button(
                onClick = { mostrar = true },
                shape = RoundedCornerShape(0.dp),
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(text = item,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Start)
            }
            DropdownMenu(
                expanded = mostrar,
                onDismissRequest = { mostrar = false }
            ) {
                lista.forEachIndexed { index, valor ->
                    DropdownMenuItem(
                        text = { Text(valor) },
                        onClick = {
                            mostrar = false
                            item=valor
                        }
                    )
                }

            }
        }
        Text(
            text = "Cantidad"
        )
        TextField(
            value = cantidad,
            onValueChange = {bean->cantidad=bean},
            modifier = Modifier
                .fillMaxWidth()
        )
        Text(text = "Precio",
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 10.dp)
        )
        Text(text = "S/. ${precio}")

        Text(text = "Total")
        Text(text = "S/. ${total}")

        Text(text = "Servicio")
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = delivery,
                onCheckedChange = {delivery=it},
                //checkmarkStroke = TODO(),
                //outlineStroke = TODO(),
                //modifier = TODO(),
                //enabled = TODO(),
                //colors = TODO(),
                // interactionSource = TODO()
            )
            Text(text = "Delivery")
        }

        Text(text = "Descuento")
        Text(text = "S/. ${descuento}")

        Text(text = "Total a pagar")
        Text(text = "S/. ${pagar}")

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 100.dp),
            shape = RoundedCornerShape(size = 0.dp),
            onClick = {
                precio = when(posicion) {
                    0 -> 0.0
                    1 -> 65.5
                    2 -> 34.5
                    3 -> 18.5
                    4 -> 17.5
                    else -> 21.5
                }

                total = precio * cantidad.toInt()
                val deli = if (delivery) 10 else 0
                descuento = if (total > 60) 5.0 else 0.0
                pagar = (total + deli) - descuento
            }
        ) {
            Text(text = "Procesar")
        }
    }
}