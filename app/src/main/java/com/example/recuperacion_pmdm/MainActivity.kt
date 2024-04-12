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
        
        findViewById(R.id.boton_Zonas)
        findViewById(R.id.boton_Favoritos)

        zonas.setOnClickListener {
            intent = Intent(ListaZonas::)
        }
    }

}