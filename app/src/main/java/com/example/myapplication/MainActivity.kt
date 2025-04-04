package com.example.myapplication

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {

    private lateinit var selectedPizzaText: TextView
    private lateinit var selectedSideBeverageText: TextView
    private lateinit var totalPriceText: TextView
    private lateinit var noteInput: TextInputEditText

    private var pizzaResult: String? = null
    private var pizzaPriceTotal: Int = 0

    private var sideResult: String? = null
    private var sidePriceTotal: Int = 0

    private val pizzaLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data = result.data?.getStringExtra("pizza_result") ?: "無"
            pizzaResult = data
            selectedPizzaText.text = if (data == "無") "披薩：無" else "披薩：\n$data"
            pizzaPriceTotal = extractPrice(data)
            updateTotalPrice()
        }
    }

    private val sideLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data = result.data?.getStringExtra("side_beverage_result") ?: "無"
            sideResult = data
            selectedSideBeverageText.text = if (data == "無") "附餐與飲料：無" else "附餐與飲料：\n$data"
            sidePriceTotal = extractPrice(data)
            updateTotalPrice()
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        selectedPizzaText = findViewById(R.id.selectedPizza)
        selectedSideBeverageText = findViewById(R.id.selectedSideBeverage)
        totalPriceText = findViewById(R.id.price)
        noteInput = findViewById(R.id.inputNote)

        findViewById<MaterialButton>(R.id.btnPizzaSelection).setOnClickListener {
            val intent = Intent(this, PizzaSelectionActivity::class.java)
            pizzaLauncher.launch(intent)
        }

        findViewById<MaterialButton>(R.id.btnSideBeverageSelection).setOnClickListener {
            val intent = Intent(this, SideBeverageSelectionActivity::class.java)
            sideLauncher.launch(intent)
        }

        findViewById<MaterialButton>(R.id.btnConfirmOrder).setOnClickListener {
            val note = noteInput.text.toString().ifBlank { "無" }
            val message = """
                ✅ 訂單已送出！

                ${pizzaResult ?: "未選擇披薩"}
                ${sideResult ?: "未選擇附餐與飲料"}
                備註：$note
                總價：$${pizzaPriceTotal + sidePriceTotal}
            """.trimIndent()

            Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        }
    }

    private fun updateTotalPrice() {
        val total = pizzaPriceTotal + sidePriceTotal
        totalPriceText.text = "總價：\$${total}"
    }

    // 從字串中提取所有 $數字，加總總價
    private fun extractPrice(text: String?): Int {
        if (text == null) return 0
        val regex = Regex("""\$(\d+)""")
        return regex.findAll(text).sumOf { it.groupValues[1].toInt() }
    }
}
