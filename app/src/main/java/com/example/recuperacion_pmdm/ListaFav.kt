package com.example.recuperacion_pmdm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class ListaFav : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_fav)
    }
    override fun onBackPressed() {
        super.onBackPressed()
        this.finish()
    }
}