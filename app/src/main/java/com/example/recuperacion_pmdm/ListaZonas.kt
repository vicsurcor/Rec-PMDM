package com.example.recuperacion_pmdm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.FileOutputStream
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

/**
 * ListaZonas class represents an activity for displaying a list of Zona objects retrieved from a KML file.
 * It initializes a ZonasAdapter to display the list using a RecyclerView.
 */
class ListaZonas : AppCompatActivity() {

    /**
     * Called when the activity is starting. It initializes the UI components and retrieves data from a KML file.
     *
     * @param savedInstanceState The saved state of the activity.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_zonas)

        // Create an instance of CrearLista and read data from the KML file
        val crearLista = CrearLista()
        crearLista.readKmlFile(resources, R.raw.banos)

        // Create a mutable list to hold Zona objects
        val data = mutableListOf<Zona>()
        // Add all Zona objects from CrearLista to the data list
        data.addAll(crearLista.lista)

        // Initialize RecyclerView and set its layout manager
        val recyclerView = findViewById<RecyclerView>(R.id.rec_Zonas)
        recyclerView.layoutManager = LinearLayoutManager(this)
        // Set up the adapter with the data list and the appropriate type
        recyclerView.adapter = ZonasAdapter(data, "lista", this)
    }

    /**
     * Called when the activity has detected the user's press of the back key.
     * It finishes the activity when the back button is pressed.
     */
    override fun onBackPressed() {
        super.onBackPressed()
        this.finish()
    }
}
