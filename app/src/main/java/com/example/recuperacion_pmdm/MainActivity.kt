package com.example.recuperacion_pmdm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.room.Room
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * MainActivity class represents the main activity of the application.
 * It provides buttons to navigate to the list of all zones and the list of favorite zones.
 */
class MainActivity : AppCompatActivity() {

    /**
     * Reference to the "Zonas" button.
     */
    private lateinit var zonas: Button

    /**
     * Reference to the "Favoritos" button.
     */
    private lateinit var favoritos: Button

    /**
     * Reference to the application database.
     */
    lateinit var appDatabase: AppDatabase

    /**
     * Called when the activity is starting. It initializes UI components and sets up click listeners for buttons.
     *
     * @param savedInstanceState The saved state of the activity.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize the application database
        appDatabase = AppDatabase.getInstance(this)

        // Delete all data from the database (using a coroutine)
        val dao = appDatabase.zonaDao()
        CoroutineScope(Dispatchers.IO).launch {
            dao.deleteAll()
        }

        // Initialize references to UI buttons
        zonas = findViewById(R.id.boton_Zonas)
        favoritos = findViewById(R.id.boton_Favoritos)

        // Set click listener for "Zonas" button to navigate to ListaZonas activity
        zonas.setOnClickListener {
            val intent = Intent(this@MainActivity, ListaZonas::class.java)
            startActivity(intent)
        }

        // Set click listener for "Favoritos" button to navigate to ListaFav activity
        favoritos.setOnClickListener {
            val intent = Intent(this@MainActivity, ListaFav::class.java)
            startActivity(intent)
        }
    }
}
