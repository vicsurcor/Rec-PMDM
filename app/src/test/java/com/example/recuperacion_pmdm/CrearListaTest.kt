package com.example.recuperacion_pmdm

import android.content.res.Resources
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mockito.*
import org.xmlpull.v1.XmlPullParserFactory
import java.io.InputStream

class CrearListaTest {

    private lateinit var crearLista: CrearLista
    private lateinit var mockResources: Resources
    private lateinit var mockInputStream: InputStream

    @BeforeEach
    fun setUp() {
        crearLista = CrearLista()
        mockResources = mock(Resources::class.java)
        mockInputStream = mock(InputStream::class.java)
    }

    @Test
    fun testLeerYAnalizarArchivoKmlValido() {
        `when`(mockResources.openRawResource(anyInt())).thenReturn(mockInputStream)

        val factory = XmlPullParserFactory.newInstance()
        val parser = factory.newPullParser()
        val kmlData = """
            <kml>
                <Placemark>
                    <ExtendedData>
                        <Data name="NOMBRE">
                            <value>Manantial de Agua salada</value>
                        </Data>
                        <Data name="LOCALIDAD">
                            <value>Carcastillo</value>
                        </Data>
                        <Data name="CALIDAD_DEL_AGUA">
                            <value>Buena</value>
                        </Data>
                    </ExtendedData>
                    <coordinates>-1.23,42.34</coordinates>
                </Placemark>
            </kml>
        """.trimIndent()

        parser.setInput(kmlData.byteInputStream(), null)

        crearLista.parseKml(parser)

        assertEquals(1, crearLista.lista.size)
        val zona = crearLista.lista[0]
        assertEquals("Manantial de Agua salada", zona.nombre)
        assertEquals("Carcastillo", zona.localidad)
        assertEquals("Buena", zona.calidad)
        assertEquals("-2.037837606992435,42.66895949073081,0", zona.coordenadas)
        assertEquals(R.drawable.imagen1, zona.imagen)
    }

    @Test
    fun testAsignacionDeImagenPorDefecto() {
        `when`(mockResources.openRawResource(anyInt())).thenReturn(mockInputStream)

        val factory = XmlPullParserFactory.newInstance()
        val parser = factory.newPullParser()
        val kmlData = """
            <kml>
                <Placemark>
                    <ExtendedData>
                        <Data name="NOMBRE">
                            <value>Zona Desconocida</value>
                        </Data>
                        <Data name="LOCALIDAD">
                            <value>Desconocida</value>
                        </Data>
                        <Data name="CALIDAD_DEL_AGUA">
                            <value>Desconocida</value>
                        </Data>
                    </ExtendedData>
                    <coordinates>-1.23,42.34</coordinates>
                </Placemark>
            </kml>
        """.trimIndent()

        parser.setInput(kmlData.byteInputStream(), null)

        crearLista.parseKml(parser)

        assertEquals(1, crearLista.lista.size)
        val zona = crearLista.lista[0]
        assertEquals("Zona Desconocida", zona.nombre)
        assertEquals("Desconocida", zona.localidad)
        assertEquals("Desconocida", zona.calidad)
        assertEquals("42.34,-1.23", zona.coordenadas)
        assertEquals(R.drawable.imagen1, zona.imagen)
    }

    @Test
    fun testManejoDeArchivoKmlConDatosFaltantes() {
        `when`(mockResources.openRawResource(anyInt())).thenReturn(mockInputStream)

        val factory = XmlPullParserFactory.newInstance()
        val parser = factory.newPullParser()
        val kmlData = """
            <kml>
                <Placemark>
                    <ExtendedData>
                        <Data name="NOMBRE">
                            <value>Foz de Benasa</value>
                        </Data>
                        <Data name="CALIDAD_DEL_AGUA">
                            <value>Excelente</value>
                        </Data>
                    </ExtendedData>
                    <coordinates>-1.23,42.34</coordinates>
                </Placemark>
            </kml>
        """.trimIndent()

        parser.setInput(kmlData.byteInputStream(), null)

        crearLista.parseKml(parser)

        assertEquals(1, crearLista.lista.size)
        val zona = crearLista.lista[0]
        assertEquals("Foz de Benasa", zona.nombre)
        assertNull(zona.localidad)
        assertEquals("Excelente", zona.calidad)
        assertEquals("42.34,-1.23", zona.coordenadas)
        assertEquals(R.drawable.imagen5, zona.imagen)
    }

    @Test
    fun testInicializacionDeListasVacias() {
        val nuevaLista = CrearLista()
        assertTrue(nuevaLista.lista.isEmpty())
        assertFalse(nuevaLista.listaFot.isEmpty())
    }

    @Test
    fun testMapeoCorrectoDeNombreARecursoDeImagen() {
        assertEquals(R.drawable.imagen1, crearLista.listaFot["Manantial de Agua salada"])
        assertEquals(R.drawable.imagen2, crearLista.listaFot["Río Aragón en Carcastillo"])
        assertEquals(R.drawable.imagen3, crearLista.listaFot["Embalse de Alloz"])
        assertEquals(R.drawable.imagen4, crearLista.listaFot["Embalse de Alloz en Úgar"])
        assertEquals(R.drawable.imagen5, crearLista.listaFot["Foz de Benasa"])
        assertEquals(R.drawable.imagen6, crearLista.listaFot["Balsa de la Morea"])
        assertEquals(R.drawable.imagen7, crearLista.listaFot["Piscina de Uztárroz"])
    }
}