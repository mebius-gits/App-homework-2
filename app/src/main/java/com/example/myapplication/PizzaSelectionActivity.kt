package com.example.myapplication

import PizzaAdapter
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView

class PizzaSelectionActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var pizzaAdapter: PizzaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_pizza_selection)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        recyclerView = findViewById(R.id.recyclerViewPizzas)

        val pizzas = listOf(
            PizzaItem("瑪格麗特披薩", 300, "蕃茄、莫札瑞拉起司", R.drawable.pizza_flavor_1),
            PizzaItem("義式香腸披薩", 500, "辣味香腸、蕃茄", R.drawable.pizza_flavor_2),
            PizzaItem("素食披薩", 200, "多種蔬菜、起司", R.drawable.pizza_flavor_3),
            PizzaItem("夏威夷披薩", 250, "火腿、鳳梨", R.drawable.pizza_flavor_4)
        )

        pizzaAdapter = PizzaAdapter(pizzas)
        recyclerView.adapter = pizzaAdapter
    }
}