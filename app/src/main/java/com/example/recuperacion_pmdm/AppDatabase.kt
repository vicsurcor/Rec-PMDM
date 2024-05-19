package com.example.recuperacion_pmdm

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * AppDatabase is an abstract class that extends RoomDatabase. It represents the database
 * instance for the application.
 *
 * @constructor Creates an instance of the AppDatabase.
 */
@Database(entities = [ZonasEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    /**
     * Provides access to the Data Access Object (DAO) for managing Zona entities.
     *
     * @return The ZonaDao instance.
     */
    abstract fun zonaDao(): ZonaDao

    /**
     * Companion object to provide methods for creating and accessing the database instance.
     */
    companion object {
        private const val DATABASE_NAME = "ZonasFav"

        @Volatile
        private var INSTANCE: AppDatabase? = null

        /**
         * Retrieves or creates a singleton instance of the AppDatabase.
         *
         * @param context The application context.
         * @return The singleton instance of AppDatabase.
         */
        fun getInstance(context: Context): AppDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    // Open connection
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        DATABASE_NAME
                    ).build()
                    INSTANCE = instance
                }

                return instance
            }
        }
    }
}
