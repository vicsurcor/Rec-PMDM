package com.example.recuperacion_pmdm

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
data class ZonasEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val nombre: String,
    val localidad: String,
    val calidad: String,
    val coordenadas: String,
    val imagen: Int
)
