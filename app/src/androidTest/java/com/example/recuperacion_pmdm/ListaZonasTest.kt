package com.example.recuperacion_pmdm

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class ListaZonasTest {
    @Test
    fun test_Zonas() {
        val activityScenario = ActivityScenario.launch(ListaZonas::class.java)

        Espresso.onView(ViewMatchers.withId(R.layout.activity_lista_zonas))

    }
    @Test
    fun test_Texto_titulo_mostrado() {
        val activityScenario = ActivityScenario.launch(ListaZonas::class.java)

        Espresso.onView(ViewMatchers.withId(R.id.titulo_Zonas))
            .check(ViewAssertions.matches(ViewMatchers.withText(R.string.lista_de_zonas)))
    }

    @Test
    fun test_RecyclerView_mostrado() {
        val activityScenario = ActivityScenario.launch(ListaZonas::class.java)
        Espresso.onView((ViewMatchers.withId(R.id.rec_Zonas)))
    }
}