package com.example.dodopizza

import java.io.Serializable

data class Pizza(
    val name: String,
    val imageUrl: String,
    val description: String,
    val ingredients: String,
    val price: Double
): Serializable