package com.example.recuperacion_pmdm

import java.io.InputStream
import android.content.res.Resources
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory

class CrearLista {


    fun readKmlFile(resources: Resources, kmlResourceId: Int) {
        // Get InputStream for the KML file
        val inputStream: InputStream = resources.openRawResource(kmlResourceId)

        // Create XMLPullParser
        val factory = XmlPullParserFactory.newInstance()
        val parser = factory.newPullParser()

        // Set input for the parser
        parser.setInput(inputStream, null)

        // Start parsing the XML/KML file
        parseKml(parser)
    }

    fun parseKml(parser: XmlPullParser) {
        var eventType = parser.eventType
        var currentElement: String? = null // Keep track of the current XML element
        var nombreValue: String? = null // To store the value of the NOMBRE element
        var coordinatesValue: String? = null // To store the value of the coordinates element
        var localidadValue: String? = null
        var calidadValue: String? = null

        while (eventType != XmlPullParser.END_DOCUMENT) {
            when (eventType) {
                XmlPullParser.START_TAG -> {
                    // Store the name of the current element
                    currentElement = parser.name
                }
                XmlPullParser.TEXT -> {
                    // Check if the current element is NOMBRE or coordinates and retrieve their values
                    if (currentElement == "SimpleData" && parser.getAttributeValue(null, "name") == "NOMBRE") {
                        nombreValue = parser.text
                    } else if (currentElement == "coordinates") {
                        coordinatesValue = parser.text
                    }
                }
                XmlPullParser.END_TAG -> {
                    // Handle end tags if necessary
                }
            }
            // Move to the next XML event
            eventType = parser.next()
        }
    }
}