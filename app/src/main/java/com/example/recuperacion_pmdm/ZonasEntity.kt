package com.example.recuperacion_pmdm

import androidx.room.Entity
import androidx.room.PrimaryKey
/**
 * ZonasEntity class represents a Zona entity in the database.
 *
 * @property id The unique identifier of the Zona entity.
 * @property nombre The name of the zone.
 * @property localidad The location of the zone.
 * @property calidad The water quality of the zone.
 * @property coordenadas The coordinates of the zone.
 * @property imagen The resource ID of the image associated with the zone.
 */
@Entity(tableName = "ZonasEntity")
data class ZonasEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val nombre: String,
    val localidad: String,
    val calidad: String,
    val coordenadas: String,
    val imagen: Int
)

