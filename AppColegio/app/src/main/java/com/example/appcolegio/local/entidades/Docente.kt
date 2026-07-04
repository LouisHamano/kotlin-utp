package com.example.appcolegio.local.entidades
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Docente (
    @PrimaryKey(autoGenerate = true)
    val codigo: Int,
    val nombres: String,
    val appellidos: String,
    val sexo: String,
    val sueldo: Double,
    val hijos: Int
)