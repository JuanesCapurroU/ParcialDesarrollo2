package com.example.parcial2.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.example.parcial2.items.carrito
import com.example.parcial2.items.productos

@Composable
fun DetalleScreen(navController: NavController, productoId: Int?) {
    val producto = productos.find { it.id == productoId }

    if (producto == null) {
        Text("Producto no encontrado", modifier = Modifier.padding(16.dp))
        return
    }

    val painter = rememberAsyncImagePainter(model = producto.imagenUrl)
    val estado = painter.state

    Column(modifier = Modifier.padding(16.dp)) {
        if (estado is AsyncImagePainter.State.Error) {
            Text("Imagen no disponible", modifier = Modifier.padding(8.dp))
        } else {
            AsyncImage(
                model = producto.imagenUrl,
                contentDescription = "Imagen producto",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text(producto.nombre, style = MaterialTheme.typography.titleLarge)
        Text("Precio: $${producto.precio}")
        Text("Descripción: ${producto.descripcion}", modifier = Modifier.padding(vertical = 8.dp))

        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            carrito.add(producto)
            navController.popBackStack()
        }) {
            Text("Agregar al Carrito")
        }

        OutlinedButton(onClick = { navController.popBackStack() }) {
            Text("Volver")
        }
    }
}
