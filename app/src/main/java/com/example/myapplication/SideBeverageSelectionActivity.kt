package com.example.myapplication

import SideBeverageItem
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
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
            SideBeverageItem(R.drawable.side_beverage_1, "紅茶", 5, 4, 3),
            SideBeverageItem(R.drawable.side_beverage_2, "檸檬水", 5, 4, 3),
            SideBeverageItem(R.drawable.side_beverage_3, "咖啡", 6, 5, 4),
            SideBeverageItem(R.drawable.side_beverage_4, "紅茶", 5, 4, 3),
            SideBeverageItem(R.drawable.side_beverage_5, "檸檬水", 5, 4, 3),
            SideBeverageItem(R.drawable.side_beverage_6, "咖啡", 6, 5, 4)
        )


        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(this, 2)  // 2 columns in grid
        recyclerView.adapter = SideBeverageAdapter(beverageList)
        var sideBeverageAdapter = SideBeverageAdapter(beverageList)

        val confirmButton = findViewById<Button>(R.id.confirmButton)
        confirmButton.setOnClickListener {
            val selected = sideBeverageAdapter.getSelectedItems()

            val result = selected.joinToString("\n") { item ->
                val price = when (item.selectedSize) {
                    "large" -> item.largePrice
                    "medium" -> item.mediumPrice
                    "small" -> item.smallPrice
                    else -> 0
                }

                val sizeInChinese = when (item.selectedSize) {
                    "large" -> "大"
                    "medium" -> "中"
                    "small" -> "小"
                    else -> "無"
                }

                "   ${item.name}（$sizeInChinese）x ${item.quantity} : \$${item.quantity * price}"
            }

            val finalResult = if (result.isBlank()) "無" else result

            val intent = Intent()
            intent.putExtra("side_beverage_result", finalResult)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }
}