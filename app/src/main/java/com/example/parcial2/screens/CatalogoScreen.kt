package com.example.parcial2.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.parcial2.items.carrito
import com.example.parcial2.items.productos
import com.example.parcial2.items.totalCarrito
import com.example.parcial2.models.Producto

// Colores personalizados (puedes moverlos a un archivo Colors.kt si deseas)
val VerdeClaro = Color(0xFF81C784)
val VerdeOscuro = Color(0xFF388E3C)

@Composable
fun CatalogoScreen(navController: NavController) {
    Scaffold(
        bottomBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    "Total carrito: $${totalCarrito()}",
                    color = VerdeOscuro,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(
                        onClick = { navController.navigate("registro") },
                        colors = ButtonDefaults.buttonColors(containerColor = VerdeClaro),
                        shape = MaterialTheme.shapes.medium
                    ) {
                        Text("Agregar Producto", color = Color.White)
                    }
                    Button(
                        onClick = { navController.navigate("carrito") },
                        colors = ButtonDefaults.buttonColors(containerColor = VerdeOscuro),
                        shape = MaterialTheme.shapes.medium
                    ) {
                        Text("Ver Carrito", color = Color.White)
                    }
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
                        .padding(vertical = 8.dp),
                    colors = CardDefaults.cardColors(containerColor = VerdeClaro.copy(alpha = 0.1f))
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
