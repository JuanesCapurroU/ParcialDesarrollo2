package com.example.parcial2.screens


import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.parcial2.items.carrito
import com.example.parcial2.items.totalCarrito

@Composable
fun CarritoScreen(navController: NavController) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Carrito de Compras", style = MaterialTheme.typography.headlineSmall)
        Spacer(Modifier.height(16.dp))

        carrito.forEach {
            Text("${it.nombre} - $${it.precio}")
        }

        Spacer(Modifier.height(16.dp))
        Text("Total: $${totalCarrito()}", style = MaterialTheme.typography.titleMedium)

        Spacer(Modifier.height(16.dp))
        Button(onClick = {
            carrito.clear()
            navController.popBackStack()
        }) {
            Text("Finalizar Compra")
        }

        OutlinedButton(onClick = { navController.popBackStack() }) {
            Text("Volver al catálogo")
        }
    }
}
