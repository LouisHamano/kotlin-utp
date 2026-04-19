package com.example.appservicios.ventanas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.Button
import androidx.compose.material3.RadioButton
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
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appservicios.modelos.ServicioData

data class producto(
    val nombre: String,
    val precio: Double,
    val instalacion: Double,
    val descuento: Double
)
@Composable
fun Servicio(irResumen:(ServicioData)-> Unit) {
    val lista = listOf(
        producto("Dúo-Cámaras de Seguridad y Alarmas contra Robos", 119.30, 37.00, 0.07),
        producto("Trio-Cámaras de Seguridad-Alarmas contra Robos/Incendio", 169.80, 65.00, 0.12),
        producto("Solo Cámaras de Seguridad", 59.50, 21.00, 0.04),
        producto("Solo Alarmas contra Robos", 49.20, 17.00, 0.04),
        producto("Solo Alarmas contra Incendio", 42.30, 15.00, 0.04),
        producto("Solo Cercos Eléctricos", 125.70, 35.00, 0.05)
    )

    var posService by remember { mutableIntStateOf(-1) }
    var nomService by remember { mutableStateOf("") }
    var cliente by remember { mutableStateOf("Maria") }
    var dni by remember { mutableStateOf("40045555") }

    var costo_servicio by remember { mutableDoubleStateOf(0.0) }
    var costo_instalacion by remember { mutableDoubleStateOf(0.0) }
    var descuento by remember { mutableDoubleStateOf(0.0) }

    var total_pagar by remember { mutableDoubleStateOf(0.0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp, vertical = 40.dp)
    ) {
        Text(text = "DATOS DEL CLIENTE",
            modifier = Modifier
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp)

        Text(text = "Cliente")
        TextField(
            value = cliente,
            onValueChange = {bean->cliente=bean},
            modifier = Modifier
                .fillMaxWidth()
        )

        Text(text = "DNI")
        TextField(
            value = dni,
            onValueChange = {bean->dni=bean},
            modifier = Modifier
                .fillMaxWidth()
        )

        /* Apartado de servicios */

        Text(text = "SERVICIO",
            modifier = Modifier
                .padding(0.dp, 18.dp, 0.dp, 0.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Left,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp)

        Column(
            modifier = Modifier.selectableGroup()
        ) {
            lista.forEachIndexed { index, producto ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .height(40.dp)
                        .fillMaxWidth()
                        .selectable(
                            selected = posService == index,
                            onClick = {
                                posService = index
                                nomService = producto.nombre
                                costo_servicio = producto.precio
                                costo_instalacion = producto.instalacion
                                descuento = producto.descuento
                            }
                        )
                ) {
                    RadioButton(
                        selected = posService == index,
                        onClick = {
                            posService = index
                            nomService = producto.nombre
                        }
                    )
                    Text(text = producto.nombre)
                }
            }
        }

        /* Apartado de Montos */

        Text(text = "MONTOS",
            modifier = Modifier
                .padding(0.dp, 18.dp, 0.dp, 0.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Left,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp)

        Text(text = "COSTO DE SERVICIO" +
                "\n${costo_servicio}",
            modifier = Modifier
                .fillMaxWidth(),
            textAlign = TextAlign.Left,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp)

        Text(text = "${costo_servicio}",
            modifier = Modifier
                .fillMaxWidth(),
            textAlign = TextAlign.Left,
            fontSize = 16.sp)

        Text(text = "COSTO INSTALACIÓN",
            modifier = Modifier
                .fillMaxWidth(),
            textAlign = TextAlign.Left,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp)

        Text(text = "${costo_instalacion}",
            modifier = Modifier
                .fillMaxWidth(),
            textAlign = TextAlign.Left,
            fontSize = 16.sp)

        Text(text = "DESCUENTO",
            modifier = Modifier
                .fillMaxWidth(),
            textAlign = TextAlign.Left,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp)

        Text(text = "${descuento}",
            modifier = Modifier
                .fillMaxWidth(),
            textAlign = TextAlign.Left,
            fontSize = 16.sp)

        Text(text = "TOTAL PAGAR",
            modifier = Modifier
                .fillMaxWidth(),
            textAlign = TextAlign.Left,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp)

        Text(text = "${total_pagar}",
            modifier = Modifier
                .fillMaxWidth(),
            textAlign = TextAlign.Left,
            fontSize = 16.sp)

        /* Apartado de Botones */

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(15.dp, Alignment.CenterHorizontally),
            modifier = Modifier
                .fillMaxSize()
        ) {
            Button(
                onClick = {
                    total_pagar = costo_servicio + costo_instalacion - (costo_servicio * descuento)
                }
            ) {
                Text(text = "Procesar")
            }
            Button(
                onClick = {irResumen(
                    ServicioData(cliente, dni, nomService, costo_servicio, costo_instalacion, descuento, total_pagar)
                )}
            ) {
                Text(text = "Resumen")
            }
        }
    }
}