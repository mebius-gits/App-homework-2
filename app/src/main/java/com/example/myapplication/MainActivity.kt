package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Handle Pizza Selection Button
        findViewById<MaterialButton>(R.id.btnPizzaSelection).setOnClickListener {
            val intent = Intent(this, PizzaSelectionActivity::class.java)
            startActivity(intent)
        }

        // Handle Side and Beverage Selection Button
        findViewById<MaterialButton>(R.id.btnSideBeverageSelection).setOnClickListener {
            val intent = Intent(this, SideBeverageSelectionActivity::class.java)
            startActivity(intent)
        }

        // Handle Confirm Order Button
        findViewById<MaterialButton>(R.id.btnConfirmOrder).setOnClickListener {
            Toast.makeText(this, "訂單已送出！", Toast.LENGTH_SHORT).show()
        }
    }
}