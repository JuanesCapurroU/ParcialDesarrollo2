package com.example.parcial2.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.parcial2.items.productos
import com.example.parcial2.models.Producto

@Composable
fun RegistroScreen(navController: NavController) {
    val verdeClaro = Color(0xFF81C784)
    val verdeOscuro = Color(0xFF388E3C)

    var nombre by remember { mutableStateOf("") }
    var precio by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }
    var imagenUrl by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Agregar Producto", style = MaterialTheme.typography.headlineSmall, color = verdeOscuro)
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = precio,
            onValueChange = { precio = it },
            label = { Text("Precio") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = descripcion,
            onValueChange = { descripcion = it },
            label = { Text("Descripción") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = imagenUrl,
            onValueChange = { imagenUrl = it },
            label = { Text("URL de imagen") },
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 56.dp, max = 56.dp),
            singleLine = true
        )

        Spacer(Modifier.height(24.dp))

        Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
            Button(
                onClick = {
                    if (
                        nombre.isNotBlank() &&
                        precio.toDoubleOrNull() != null &&
                        descripcion.isNotBlank() &&
                        imagenUrl.isNotBlank()
                    ) {
                        productos.add(
                            Producto(
                                id = productos.size + 1,
                                nombre = nombre,
                                precio = precio.toDouble(),
                                descripcion = descripcion,
                                imagenUrl = imagenUrl
                            )
                        )
                        navController.navigate("catalogo")
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = verdeOscuro),
                modifier = Modifier.weight(1f).padding(end = 8.dp)
            ) {
                Text("Guardar", color = Color.White)
            }

            OutlinedButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier.weight(1f).padding(start = 8.dp),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = verdeOscuro)
            ) {
                Text("Cancelar")
            }
        }
    }
}
