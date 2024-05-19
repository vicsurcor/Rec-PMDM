package com.example.recuperacion_pmdm

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
/**
 * ZonaDao is a Data Access Object (DAO) interface for accessing Zona entities from the database.
 */
@Dao
interface ZonaDao {

    /**
     * Retrieves all Zona entities from the database.
     *
     * @return A list of all Zona entities.
     */
    @Query("SELECT * FROM ZonasEntity")
    fun getAll(): List<ZonasEntity>

    /**
     * Deletes all Zona entities from the database.
     */
    @Query("DELETE FROM ZonasEntity")
    fun deleteAll()

    /**
     * Inserts a new Zona entity into the database.
     *
     * @param zona The Zona entity to insert.
     */
    @Insert
    fun insert(zona: ZonasEntity)

    /**
     * Deletes a Zona entity from the database by its name.
     *
     * @param nombre The name of the Zona entity to delete.
     */
    @Query("DELETE FROM ZonasEntity WHERE nombre == :nombre")
    fun deleteById(nombre: String)
}
