package com.example.parcial2.screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.parcial2.items.carrito
import com.example.parcial2.items.productos
import com.example.parcial2.items.totalCarrito
import com.example.parcial2.models.Producto

@Composable
fun CatalogoScreen(navController: NavController) {
    Scaffold(
        bottomBar = {
            Column {
                Text("Total carrito: $${totalCarrito()}")
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                    Button(onClick = { navController.navigate("registro") }) { Text("Agregar Producto") }
                    Button(onClick = { navController.navigate("carrito") }) { Text("Ver Carrito") }
                }
            }
        }
    ) { padding ->
        LazyColumn(modifier = Modifier.padding(padding).padding(16.dp)) {
            items(productos) { producto ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { navController.navigate("detalle/${producto.id}") }
                        .padding(vertical = 8.dp)
                ) {
                    Row(modifier = Modifier.padding(16.dp)) {
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(producto.imagenUrl)
                                .crossfade(true)
                                .build(),
                            contentDescription = "Producto",
                            modifier = Modifier.size(80.dp)
                        )
                        Spacer(Modifier.width(12.dp))
                        Column {
                            Text(producto.nombre, style = MaterialTheme.typography.titleMedium)
                            Text("Precio: $${producto.precio}")
                        }
                    }
                }
            }
        }
    }
}
