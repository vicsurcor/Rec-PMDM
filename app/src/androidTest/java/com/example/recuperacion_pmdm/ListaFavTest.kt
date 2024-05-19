package com.example.recuperacion_pmdm

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class ListaFavTest{
    @Test
    fun test_Favoritos() {
        val activityScenario = ActivityScenario.launch(ListaFav::class.java)

        Espresso.onView(ViewMatchers.withId(R.layout.activity_lista_fav))

    }
    @Test
    fun test_Texto_titulo_mostrado() {
        val activityScenario = ActivityScenario.launch(ListaFav::class.java)

        Espresso.onView(ViewMatchers.withId(R.id.titulo_Fav))
            .check(ViewAssertions.matches(ViewMatchers.withText(R.string.favoritos)))
    }

    @Test
    fun test_RecyclerView_mostrado() {
        val activityScenario = ActivityScenario.launch(ListaFav::class.java)
        Espresso.onView((ViewMatchers.withId(R.id.rec_Favoritos)))
    }
}