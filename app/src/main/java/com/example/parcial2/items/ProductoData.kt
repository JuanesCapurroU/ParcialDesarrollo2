package com.example.parcial2.items

import androidx.compose.runtime.mutableStateListOf
import com.example.parcial2.models.Producto

val productos = mutableStateListOf<Producto>()
val carrito = mutableStateListOf<Producto>()

fun totalCarrito(): Double = carrito.sumOf { it.precio }
