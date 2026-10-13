package com.example.rickmortyapp

// Datos del personaje para identificarlo
data class Personaje(
    val id: Int,
    val name: String,
    val image: String
)

// La API devuelve los personajes dentro de "results".
data class RespuestaPersonajes(
    val results: List<Personaje>
)