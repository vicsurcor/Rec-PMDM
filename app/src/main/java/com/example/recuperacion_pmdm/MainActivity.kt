package com.example.recuperacion_pmdm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    private lateinit var zonas: Button
    private lateinit var favoritos: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        zonas = findViewById(R.id.boton_Zonas)
        favoritos = findViewById(R.id.boton_Favoritos)

        zonas.setOnClickListener {
            val intent = Intent(this@MainActivity, ListaZonas::class.java)
            startActivity(intent)
        }
        favoritos.setOnClickListener {
            val intent = Intent(this@MainActivity, ListaFav::class.java)
            startActivity(intent)
        }
    }

}