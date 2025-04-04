package com.example.myapplication

data class PizzaItem(
    val name: String,
    val price: Int,
    val description: String,
    val imageResId: Int,
    var isChecked: Boolean = false,
    var quantity: Int = 1
)
