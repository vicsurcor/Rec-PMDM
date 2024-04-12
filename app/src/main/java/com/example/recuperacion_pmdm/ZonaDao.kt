package com.example.recuperacion_pmdm

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
@Dao
interface ZonaDao {

    @Query("Select * FROM ZonasEntity")
    fun getAll(): List<ZonasEntity>
    @Query("DELETE FROM ZonasEntity")
    fun deleteAll()
    @Insert
    fun insert(zona: ZonasEntity)
    @Query("DELETE FROM ZonasEntity WHERE nombre == :nombre")
    fun deleteById(nombre: String)


}