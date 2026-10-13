package com.example.rickmortyapp

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RickMortyApiService {

    // Consultamos la página de Rick y Morty  https://rickandmortyapi.com/api/character
    @GET("character")
    suspend fun obtenerPersonajes(): RespuestaPersonajes
}

object RickMortyApi {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://rickandmortyapi.com/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val servicio: RickMortyApiService by lazy {
        retrofit.create(RickMortyApiService::class.java)
    }
}