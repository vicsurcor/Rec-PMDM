package com.example.recuperacion_pmdm

/**
 * Zona class represents a geographical zone entity.
 *
 * @property nombre The name of the zone.
 * @property localidad The location of the zone.
 * @property calidad The water quality of the zone.
 * @property coordenadas The coordinates of the zone.
 * @property imagen The resource ID of the image associated with the zone.
 */
class Zona(
    val nombre: String,
    val localidad: String,
    val calidad: String,
    val coordenadas: String,
    val imagen: Int
)
