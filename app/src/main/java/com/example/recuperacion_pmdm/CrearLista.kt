package com.example.recuperacion_pmdm

import java.io.InputStream
import android.content.res.Resources
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory

class CrearLista {

    val lista: MutableList<Zona> = mutableListOf()
    val listaFot: MutableMap<String, Int> = mutableMapOf(
        "Manantial de Agua salada" to R.drawable.imagen1,
        "Río Aragón en Carcastillo" to R.drawable.imagen2,
        "Embalse de Alloz" to R.drawable.imagen3,
        "Embalse de Alloz en Úgar" to R.drawable.imagen4,
        "Foz de Benasa" to R.drawable.imagen5,
        "Balsa de la Morea" to R.drawable.imagen6,
        "Piscina de Uztárroz" to R.drawable.imagen7
    )


    fun readKmlFile(resources: Resources, kmlResourceId: Int) {

        val inputStream: InputStream = resources.openRawResource(kmlResourceId)

        val factory = XmlPullParserFactory.newInstance()
        val parser = factory.newPullParser()

        parser.setInput(inputStream, null)

        parseKml(parser)
    }

    fun parseKml(parser: XmlPullParser) {

        var eventType = parser.eventType
        var nombreValue: String? = null
        var coordenadasValue: String? = null
        var localidadValue: String? = null
        var calidadValue: String? = null

        while (eventType != XmlPullParser.END_DOCUMENT) {
            when (eventType) {
                XmlPullParser.START_TAG -> {
                    val currentElement = parser.name
                    if (currentElement == "SimpleData") {
                        val attributeName = parser.getAttributeValue(null, "name")
                        when (attributeName) {
                            "NOMBRE" -> nombreValue = parser.nextText()
                            "LOCALIDAD" -> localidadValue = parser.nextText()
                            "CALIDAD_DEL_AGUA" -> calidadValue = parser.nextText()
                        }
                    } else if (currentElement == "coordinates") {
                        coordenadasValue = parser.nextText()
                        val coord = coordenadasValue.toString().split(",")
                        coordenadasValue = "${coord[1]},${coord[0]}"
                    }
                }
                XmlPullParser.END_TAG -> {
                    if (parser.name == "Placemark") {
                        val defaultImageResourceId = R.drawable.imagen1
                        val resourceId = listaFot[nombreValue.toString()] ?: defaultImageResourceId

                        lista.add(Zona(nombreValue.toString(), localidadValue.toString(), calidadValue.toString(), coordenadasValue.toString(), resourceId
                        ))

                        nombreValue = null
                        localidadValue = null
                        calidadValue = null
                        coordenadasValue = null
                    }
                }
            }
            eventType = parser.next()
        }
    }

}