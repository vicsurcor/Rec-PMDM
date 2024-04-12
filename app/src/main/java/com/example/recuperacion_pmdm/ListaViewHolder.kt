package com.example.recuperacion_pmdm

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class ZonasAdapter (private val zonas: List<Zona>, tipo: String) : RecyclerView.Adapter<ZonasAdapter.ViewHolder>() {
    val tipo = tipo
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val row: View = LayoutInflater.from(parent.context).inflate(R.layout.lista_item, parent, false)
        return ViewHolder(row, tipo)
    }

    override fun getItemCount() = zonas.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val zona = zonas[position]
        holder.bindRow(zona)
    }
    class ViewHolder(view: View, tipo: String) : RecyclerView.ViewHolder(view) {
        val nombre: TextView = view.findViewById(R.id.item_Nombre)
        val localidad: TextView = view.findViewById(R.id.item_Localidad)
        val calidad: View = view.findViewById(R.id.item_Calidad)
        val coordenadas: TextView = view.findViewById(R.id.item_Coordenadas)
        val favoritos: ImageButton = view.findViewById(R.id.item_Favoritos)
        val imagen: ImageView = view.findViewById(R.id.item_Imagen)
        val tipo = tipo

        fun bindRow(zona: Zona) {
            nombre.text = zona.nombre
            localidad.text = zona.localidad
            if (zona.calidad == "Excelente"){
                calidad.setBackgroundColor(Color.rgb(0,100,45))
            }
            if (zona.calidad == "Buena"){
                calidad.setBackgroundColor(Color.rgb(80,70,0))
            }
            if (zona.calidad == "Insuficiente") {
                calidad.setBackgroundColor(Color.RED)
            }
            coordenadas.text = zona.coordenadas
            if (tipo == "lista") {
                favoritos.setImageResource(R.drawable.ic_favoritos_no)
            } else {
                favoritos.setImageResource(R.drawable.ic_favoritos_si)
            }
            imagen.setImageResource(zona.imagen)

        }
    }


}