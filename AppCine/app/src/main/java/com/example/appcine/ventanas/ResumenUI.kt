package com.example.appcine.ventanas

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appcine.R
import com.example.appcine.modelos.ReservaData

@Composable
fun Resumen(bean: ReservaData){

    //mapa para almacenar las imagenes
    val imagenes=mapOf(
        (0 to 0) to R.drawable.p00,
        (0 to 1) to R.drawable.p01,
        (1 to 0) to R.drawable.p10,
        (1 to 1) to R.drawable.p11,
        (1 to 2) to R.drawable.p12,
    )
    //acceder a la imagen según la clave(Pair)
    val imagen:Int=imagenes[bean.posGenero to bean.posPelicula]?: R.drawable.notfound

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp, vertical = 40.dp)
    ) {
        Text(
            text = "RESUMEN",
            modifier = Modifier
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )
        Column(){
            Image(
                painter = painterResource(imagen),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
            )
        }




    }
}

