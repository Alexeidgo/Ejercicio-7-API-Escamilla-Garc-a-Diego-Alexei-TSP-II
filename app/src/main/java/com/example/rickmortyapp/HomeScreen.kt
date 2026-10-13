package com.example.rickmortyapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable

//Ponemos los elementos de la pantalla principal
fun HomeScreen(
    personajes: List<Personaje>,
    cargando: Boolean,
    hayError: Boolean,
    onReintentar: () -> Unit,
    modifier: Modifier = Modifier
) {
    when {
        //Texto para cargar personajes
        cargando -> {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("Cargando personajes…")
            }
        }

        hayError -> {
            Column(
                modifier = modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("No se pudieron cargar los personajes.")

                Button(onClick = onReintentar) {
                    Text("Reintentar")
                }
            }
        }
        //Mostrar a personajes
        else -> {
            ResultScreen(
                personajes = personajes,
                modifier = modifier
            )
        }
    }
}

@Composable
//Desplegar a los personajes en la app.
fun ResultScreen(
    personajes: List<Personaje>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(
            items = personajes,
            key = { personaje -> personaje.id }
        ) { personaje ->
            Card(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column {
                    AsyncImage(
                        model = personaje.image,
                        contentDescription = personaje.name,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(1f)
                    )

                    Text(
                        text = personaje.name,
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun AsyncImage(
    model: String,
    contentDescription: String,
    contentScale: ContentScale,
    modifier: Modifier
) {
    TODO("Not yet implemented")
}