package com.example.parcial2.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.parcial2.items.carrito
import com.example.parcial2.items.totalCarrito

@Composable
fun CarritoScreen(navController: NavController) {
    val verdeClaro = Color(0xFF81C784)
    val verdeOscuro = Color(0xFF388E3C)

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Carrito de Compras",
            style = MaterialTheme.typography.headlineSmall,
            color = verdeOscuro
        )

        Spacer(Modifier.height(16.dp))

        carrito.forEach {
            Text("${it.nombre} - $${it.precio}")
        }

        Spacer(Modifier.height(16.dp))

        Text(
            text = "Total: $${totalCarrito()}",
            style = MaterialTheme.typography.titleMedium,
            color = verdeOscuro
        )

        Spacer(Modifier.height(24.dp))

        Button(
            onClick = {
                carrito.clear()
                navController.popBackStack()
            },
            colors = ButtonDefaults.buttonColors(containerColor = verdeOscuro),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Finalizar Compra", color = Color.White)
        }

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedButton(
            onClick = { navController.popBackStack() },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = verdeOscuro)
        ) {
            Text("Volver al catálogo")
        }
    }
}
