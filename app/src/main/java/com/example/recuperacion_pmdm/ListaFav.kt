package com.example.recuperacion_pmdm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * ListaFav class represents an activity for displaying a list of favorite Zona objects.
 * It retrieves data from the database and displays it using a RecyclerView.
 */
class ListaFav : AppCompatActivity() {
    val username = intent.getStringExtra("username") ?: "Unknown User"
    /**
     * Reference to the application database.
     */
    lateinit var appDatabase: AppDatabase

    /**
     * Reference to the RecyclerView for displaying the list.
     */
    lateinit var recyclerView: RecyclerView

    /**
     * Adapter for managing data in the RecyclerView.
     */
    lateinit var zonasAdapter: ZonasAdapter

    /**
     * Called when the activity is starting. It initializes the UI components and retrieves data from the database.
     *
     * @param savedInstanceState The saved state of the activity.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_fav)

        val username = intent.getStringExtra("username") ?: "Unknown User"
        appDatabase = AppDatabase.getInstance(this)

        recyclerView = findViewById(R.id.rec_Favoritos)
        recyclerView.layoutManager = LinearLayoutManager(this)


        // Retrieve data from the database asynchronously
        CoroutineScope(Dispatchers.IO).launch {
            val list = appDatabase.zonaDao().getAll()
            val data = list.map { zona ->
                Zona(zona.nombre, zona.localidad, zona.calidad, zona.coordenadas, zona.imagen)
            }
            // Update UI on the main thread
            withContext(Dispatchers.Main) {
                zonasAdapter = ZonasAdapter(data, "", this@ListaFav)
                recyclerView.adapter = zonasAdapter
            }
        }
    }
    fun toChat() {
        val intent = Intent(this@ListaFav, ChatActivity::class.java)
        intent.putExtra("username", username)
        startActivity(intent)
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


