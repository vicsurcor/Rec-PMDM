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

class ListaZonas : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_zonas)

        val crearLista = CrearLista()
        crearLista.readKmlFile(resources, R.raw.banos)
        val data = mutableListOf<Zona>()
        data.addAll(crearLista.lista)

        val recyclerView = findViewById<RecyclerView>(R.id.rec_Zonas)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = ZonasAdapter(data,"lista", this)

    }

    override fun onBackPressed() {
        super.onBackPressed()
        this.finish()
    }
}