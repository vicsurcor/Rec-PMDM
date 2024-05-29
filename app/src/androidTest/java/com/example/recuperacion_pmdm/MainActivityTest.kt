package com.example.recuperacion_pmdm

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Test
    fun test_MainActivity() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)

        onView(withId(R.layout.activity_mainmenu))

    }
    @Test
    fun test_Visibilidad_titulo_y_botones() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)

        onView(withId(R.id.titulo))
            .check(matches(isDisplayed()))

        onView(withId(R.id.boton_Favoritos))
            .check(matches(isDisplayed()))

        onView(withId(R.id.boton_Zonas))
            .check(matches(isDisplayed()))

    }
    @Test
    fun test_Texto_titulo_mostrado() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)

        onView(withId(R.id.titulo))
            .check(matches(withText(R.string.aplicaci_n_de_seguimiento_de_zonas_de_ba_o_naturales_en_navarra)))
    }
    @Test
    fun test_Navegacion_a_lista() {

        val activityScenario = ActivityScenario.launch(MainActivity::class.java)

        onView(withId(R.id.boton_Zonas)).perform(click())
        onView(withId(R.layout.activity_lista_zonas)).check(matches(isDisplayed()))
    }
    @Test
    fun test_Navegacion_a_favoritos() {

        val activityScenario = ActivityScenario.launch(MainActivity::class.java)

        onView(withId(R.id.boton_Zonas)).perform(click())
        onView(withId(R.layout.activity_lista_fav)).check(matches(isDisplayed()))
    }


}
