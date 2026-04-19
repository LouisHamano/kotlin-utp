package com.example.appservicios.modelos

data class ServicioData (
    val cliente: String,
    val dni: String,
    val servicio: String,
    val costo_servicio: Double,
    val costo_instalacion: Double,
    val descuento: Double,
    val total: Double
)