package com.example.parcial2.screens


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun Navegacion(navController: NavHostController) {
    NavHost(navController, startDestination = "catalogo") {
        composable("catalogo") { CatalogoScreen(navController) }
        composable("registro") { RegistroScreen(navController) }
        composable("detalle/{productoId}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("productoId")?.toIntOrNull()
            DetalleScreen(navController, id)
        }
        composable("carrito") { CarritoScreen(navController) }
    }
}
