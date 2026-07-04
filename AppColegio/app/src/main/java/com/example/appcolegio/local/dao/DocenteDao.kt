package com.example.appcolegio.local.dao

import androidx.room.Dao
import com.example.appcolegio.local.entidades.Docente

@Dao
interface DocenteDao {
    suspend fun listar(): List<Docente>
    suspend fun registrar(bean: Docente)
}