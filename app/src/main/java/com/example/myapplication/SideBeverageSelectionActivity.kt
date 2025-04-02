package com.example.myapplication

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class SideBeverageSelectionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_side_beverage_selection)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val beverageList = listOf(
            SideBeverageItem(R.drawable.side_beverage_1, "Iced Tea", 5, 4, 3),
            SideBeverageItem(R.drawable.side_beverage_2, "Lemonade", 5, 4, 3),
            SideBeverageItem(R.drawable.side_beverage_3, "Coffee", 6, 5, 4),
            SideBeverageItem(R.drawable.side_beverage_4, "Iced Tea", 5, 4, 3),
            SideBeverageItem(R.drawable.side_beverage_5, "Lemonade", 5, 4, 3),
            SideBeverageItem(R.drawable.side_beverage_6, "Coffee", 6, 5, 4),

        )

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(this, 2)  // 2 columns in grid
        recyclerView.adapter = BeverageAdapter(beverageList)
    }
}