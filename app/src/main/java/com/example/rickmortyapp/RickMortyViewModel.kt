package com.example.rickmortyapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.launch


class RickMortyViewModel : ViewModel() {

    var personajes by mutableStateOf<List<Personaje>>(emptyList())
        private set

    var cargando by mutableStateOf(false)
        private set

    var hayError by mutableStateOf(false)
        private set

    init {
        cargarPersonajes()
    }

    fun cargarPersonajes() {
        // Evita iniciar otra consulta si una sigue en proceso.
        if (cargando) return

        cargando = true
        hayError = false
        //Para obtener los recursos de los personajes debemos cargarlos y si no hay ningún
        //error la app funcionará correctamente :D
        viewModelScope.launch {
            try {
                personajes = RickMortyApi.servicio
                    .obtenerPersonajes()
                    .results
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                hayError = true
            } finally {
                cargando = false
            }
        }
    }
}