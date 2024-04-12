package com.example.recuperacion_pmdm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ListaFav : AppCompatActivity() {
    lateinit var appDatabase: AppDatabase
    lateinit var recyclerView: RecyclerView
    lateinit var zonasAdapter: ZonasAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_fav)

        // Initialize appDatabase
        appDatabase = AppDatabase.getInstance(this)

        // Initialize RecyclerView
        recyclerView = findViewById(R.id.rec_Favoritos)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Perform database query asynchronously
        CoroutineScope(Dispatchers.IO).launch {
            val list = appDatabase.zonaDao().getAll()
            val data = list.map { zona ->
                Zona(zona.nombre, zona.localidad, zona.calidad, zona.coordenadas, zona.imagen)
            }
            withContext(Dispatchers.Main) {
                // Update UI with the retrieved data
                zonasAdapter = ZonasAdapter(data, "", this@ListaFav)
                recyclerView.adapter = zonasAdapter
            }
        }
    }
    override fun onBackPressed() {
        super.onBackPressed()
        this.finish()
    }

}

