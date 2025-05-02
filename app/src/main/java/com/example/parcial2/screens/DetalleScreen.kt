package com.example.parcial2.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.example.parcial2.items.carrito
import com.example.parcial2.items.productos
import com.example.parcial2.models.Producto

@Composable
fun DetalleScreen(navController: NavController, productoId: Int?) {
    val verdeClaro = Color(0xFF81C784)
    val verdeOscuro = Color(0xFF388E3C)

    val producto = productos.find { it.id == productoId }

    if (producto == null) {
        Text(
            text = "Producto no encontrado",
            modifier = Modifier.padding(16.dp),
            color = verdeOscuro
        )
        return
    }

    val painter = rememberAsyncImagePainter(model = producto.imagenUrl)
    val estado = painter.state

    var cantidad by remember { mutableStateOf(1) }
    val total = producto.precio * cantidad

    Column(modifier = Modifier.padding(16.dp)) {
        if (estado is AsyncImagePainter.State.Error) {
            Text("Imagen no disponible", modifier = Modifier.padding(8.dp), color = Color.Red)
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

        Text(
            text = producto.nombre,
            style = MaterialTheme.typography.titleLarge,
            color = verdeOscuro
        )
        Text("Precio unitario: $${producto.precio}")
        Text("Descripción: ${producto.descripcion}", modifier = Modifier.padding(vertical = 8.dp))

        Spacer(modifier = Modifier.height(16.dp))

        // Contador de cantidad
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedButton(
                onClick = { if (cantidad > 1) cantidad-- },
                colors = ButtonDefaults.outlinedButtonColors(contentColor = verdeOscuro)
            ) {
                Text("➖")
            }

            Text(
                text = " $cantidad ",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            OutlinedButton(
                onClick = { cantidad++ },
                colors = ButtonDefaults.outlinedButtonColors(contentColor = verdeOscuro)
            ) {
                Text("➕")
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                repeat(cantidad) {
                    carrito.add(producto)
                }
                navController.popBackStack()
            },
            colors = ButtonDefaults.buttonColors(containerColor = verdeOscuro),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Agregar $cantidad por $${"%.2f".format(total)}", color = Color.White)
        }

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedButton(
            onClick = { navController.popBackStack() },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = verdeOscuro)
        ) {
            Text("Volver")
        }
    }
}
