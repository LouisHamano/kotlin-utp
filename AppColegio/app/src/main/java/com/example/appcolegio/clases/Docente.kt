package com.example.appcolegio.clases

data class Docente (
    val codigo: Int,
    val nombres: String,
    val appellidos: String,
    val sexo: String,
    val sueldo: Double,
    val hijos: Int
) { }