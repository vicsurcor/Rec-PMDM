package com.example.recuperacion_pmdm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

        val url = "https://datosabiertos.navarra.es/dataset/5ac1c99b-1465-4167-b6bd-661182c55b70/resource/81f9fc1b-230c-4745-9950-b385f71cea67/download/banos.kml"
        val filePath = "/res"

        GlobalScope.launch {
            val result = downloadFile(url, filePath)
            if (result) {
                println("Descarga Completa")
            } else {
                println("Descarga Fallida")
            }
        }

        val zona = Zona("Manantial de Agua salada","Estella / Lizarra","Excelente","42.66895949073081,-2.037837606992435",R.drawable.imagen1)
        val zona2 = Zona("Manantial de Agua salada","Estella / Lizarra","Buena","42.66895949073081,-2.037837606992435",R.drawable.imagen1)
        val zona3 = Zona("Manantial de Agua salada","Estella / Lizarra","Insuficiente","42.66895949073081,-2.037837606992435",R.drawable.imagen1)
        val data = listOf(zona,zona2,zona3)

        val recyclerView = findViewById<RecyclerView>(R.id.rec_Zonas)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = ZonasAdapter(data,"lista")
    }

    override fun onBackPressed() {
        super.onBackPressed()
        this.finish()
    }

    suspend fun downloadFile(url: String, filePath: String): Boolean {
        var inputStream: InputStream? = null
        var outputStream: FileOutputStream? = null
        var connection: HttpURLConnection? = null

        return withContext(Dispatchers.IO) {
            try {
                val url = URL(url)
                connection = url.openConnection() as HttpURLConnection
                connection!!.requestMethod = "GET"
                connection!!.connect()

                if (connection!!.responseCode != HttpURLConnection.HTTP_OK) {
                    return@withContext false
                }

                inputStream = connection!!.inputStream
                outputStream = FileOutputStream(filePath)

                val buffer = ByteArray(1024)
                var bytesRead: Int

                while (inputStream!!.read(buffer).also { bytesRead = it } != -1) {
                    outputStream!!.write(buffer, 0, bytesRead)
                }

                return@withContext true
            } catch (e: Exception) {
                e.printStackTrace()
                return@withContext false
            } finally {
                try {
                    inputStream?.close()
                    outputStream?.close()
                    connection?.disconnect()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

}