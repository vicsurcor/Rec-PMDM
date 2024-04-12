package com.example.recuperacion_pmdm

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ZonasEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun zonaDao(): ZonaDao

    companion object{
        private const val DATABASE_NAME = "ZonasFav"

        @Volatile
        private var INSTANCE : AppDatabase? = null

        fun getInstance(context: Context): AppDatabase{
            synchronized(this){

                var instance = INSTANCE

                if (instance == null){
                    // Abrir conexión
                    instance = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, DATABASE_NAME).build()
                    INSTANCE = instance
                }

                return instance
            }
        }

    }
}