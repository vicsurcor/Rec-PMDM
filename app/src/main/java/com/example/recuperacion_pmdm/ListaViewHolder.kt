package com.example.recuperacion_pmdm

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


/**
 * ZonasAdapter class represents an adapter for managing Zona objects in a RecyclerView.
 *
 * @property zonas The list of Zona objects to be displayed.
 * @property tipo The type of adapter, whether it's for a regular list or a favorites list.
 * @property context The context of the application or activity.
 */
class ZonasAdapter(private val zonas: List<Zona>, tipo: String, context: Context) :
    RecyclerView.Adapter<ZonasAdapter.ViewHolder>() {

    /**
     * The type of adapter.
     */
    val tipo = tipo

    /**
     * The context of the application or activity.
     */
    val context = context

    /**
     * Called when RecyclerView needs a new ViewHolder of the given type to represent an item.
     *
     * @param parent The ViewGroup into which the new View will be added after it is bound to an adapter position.
     * @param viewType The view type of the new View.
     * @return A new ViewHolder that holds a View of the given view type.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val row: View =
            LayoutInflater.from(parent.context).inflate(R.layout.lista_item, parent, false)
        return ViewHolder(row, tipo, context)
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    override fun getItemCount() = zonas.size

    /**
     * Called by RecyclerView to display the data at the specified position.
     *
     * @param holder The ViewHolder which should be updated to represent the contents of the item at the given position.
     * @param position The position of the item within the adapter's data set.
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val zona = zonas[position]
        holder.bindRow(zona)
    }

    /**
     * ViewHolder class represents a single item view in the RecyclerView.
     *
     * @property view The root View of the item layout.
     * @property tipo The type of adapter.
     * @property context The context of the application or activity.
     */
    class ViewHolder(view: View, tipo: String, context: Context) : RecyclerView.ViewHolder(view) {
        val nombre: TextView = view.findViewById(R.id.item_Nombre)
        val localidad: TextView = view.findViewById(R.id.item_Localidad)
        val calidad: View = view.findViewById(R.id.item_Calidad)
        val coordenadas: TextView = view.findViewById(R.id.item_Coordenadas)
        val favoritos: ImageButton = view.findViewById(R.id.item_Favoritos)
        val imagen: ImageView = view.findViewById(R.id.item_Imagen)

        /**
         * The type of adapter.
         */
        val tipo = tipo

        /**
         * The context of the application or activity.
         */
        val context = context

        /**
         * Binds Zona data to the item view.
         *
         * @param zona The Zona object to bind.
         */
        fun bindRow(zona: Zona) {
            nombre.text = zona.nombre
            localidad.text = zona.localidad
            when (zona.calidad) {
                "Excelente" -> calidad.setBackgroundColor(Color.rgb(0, 100, 45))
                "Buena" -> calidad.setBackgroundColor(Color.rgb(80, 70, 0))
                "Insuficiente" -> calidad.setBackgroundColor(Color.RED)
            }
            coordenadas.text = zona.coordenadas
            if (tipo == "lista") {
                favoritos.setImageResource(R.drawable.ic_favoritos_no)
                favoritos.setOnClickListener {
                    favoritos.setImageResource(R.drawable.ic_favoritos_si)
                    val fav = ZonasEntity(0, zona.nombre, zona.localidad, zona.calidad, zona.coordenadas, zona.imagen)
                    val db = AppDatabase.getInstance(context.applicationContext)
                    GlobalScope.launch(Dispatchers.IO) {
                        db.zonaDao().insert(fav)
                    }
                }
            } else {
                favoritos.setImageResource(R.drawable.ic_favoritos_si)
                favoritos.setOnClickListener {
                    favoritos.setImageResource(R.drawable.ic_favoritos_no)
                    val fav = ZonasEntity(0, zona.nombre, zona.localidad, zona.calidad, zona.coordenadas, zona.imagen)
                    val db = AppDatabase.getInstance(context.applicationContext)
                    GlobalScope.launch(Dispatchers.IO) {
                        db.zonaDao().deleteById(fav.nombre)
                    }
                }
            }
            imagen.setImageResource(zona.imagen)
        }
    }
}
