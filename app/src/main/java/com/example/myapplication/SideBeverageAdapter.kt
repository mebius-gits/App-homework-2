package com.example.myapplication

import SideBeverageItem
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView

class SideBeverageAdapter(private val itemList: List<SideBeverageItem>) :
    RecyclerView.Adapter<SideBeverageAdapter.BeverageViewHolder>() {

    inner class BeverageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.sideBeverageImage)
        val name: TextView = view.findViewById(R.id.sideBeverageName)
        val radioGroup: RadioGroup = view.findViewById(R.id.radioGroupSideBeverage)
        val radioLarge: RadioButton = view.findViewById(R.id.largePrice)
        val radioMedium: RadioButton = view.findViewById(R.id.mediumPrice)
        val radioSmall: RadioButton = view.findViewById(R.id.smallPrice)
        val quantityInput: EditText = view.findViewById(R.id.quantityInput)
        val increaseButton: TextView = view.findViewById(R.id.increaseButton)
        val decreaseButton: TextView = view.findViewById(R.id.decreaseButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeverageViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_side_beverage_selection, parent, false)
        return BeverageViewHolder(view)
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: BeverageViewHolder, position: Int) {
        val item = itemList[position]

        holder.image.setImageResource(item.imageResId)
        holder.name.text = item.name
        holder.radioLarge.text = "大 \$${item.largePrice}"
        holder.radioMedium.text = "中 \$${item.mediumPrice}"
        holder.radioSmall.text = "小 \$${item.smallPrice}"

        // 初始化
        holder.radioGroup.clearCheck()
        holder.quantityInput.setText("0")
        holder.quantityInput.isEnabled = false
        holder.quantityInput.setTextColor(0xFFA0A0A0.toInt())
        holder.increaseButton.isEnabled = false
        holder.decreaseButton.isEnabled = false
        holder.increaseButton.setTextColor(0xFFA0A0A0.toInt())
        holder.decreaseButton.setTextColor(0xFFA0A0A0.toInt())

        // 自行管理選取狀態
        var selectedSize: String? = null

        val radioButtons = mapOf(
            "large" to holder.radioLarge,
            "medium" to holder.radioMedium,
            "small" to holder.radioSmall
        )

        radioButtons.forEach { (size, button) ->
            button.setOnClickListener {
                if (selectedSize == size) {
                    // 點到相同的 → 取消選取
                    button.isChecked = false
                    selectedSize = null
                    item.selectedSize = null
                    item.quantity = 0

                    holder.quantityInput.setText("0")
                    holder.quantityInput.isEnabled = false
                    holder.quantityInput.setTextColor(0xFFA0A0A0.toInt())
                    holder.increaseButton.isEnabled = false
                    holder.increaseButton.setTextColor(0xFFA0A0A0.toInt())
                    holder.decreaseButton.isEnabled = false
                    holder.decreaseButton.setTextColor(0xFFA0A0A0.toInt())
                } else {
                    // 新選取
                    selectedSize = size
                    item.selectedSize = size
                    item.quantity = 1

                    holder.radioLarge.isChecked = size == "large"
                    holder.radioMedium.isChecked = size == "medium"
                    holder.radioSmall.isChecked = size == "small"

                    holder.quantityInput.setText("1")
                    holder.quantityInput.isEnabled = true
                    holder.quantityInput.setTextColor(0xFF000000.toInt())
                    holder.increaseButton.isEnabled = true
                    holder.increaseButton.setTextColor(0xFFB03A1E.toInt())
                    holder.decreaseButton.isEnabled = false
                    holder.decreaseButton.setTextColor(0xFFA0A0A0.toInt())
                }
            }
        }

        holder.increaseButton.setOnClickListener {
            if (holder.quantityInput.isEnabled) {
                item.quantity++
                holder.quantityInput.setText(item.quantity.toString())
                if (item.quantity > 1) {
                    holder.decreaseButton.isEnabled = true
                    holder.decreaseButton.setTextColor(0xFFB03A1E.toInt())
                }
            }
        }

        holder.decreaseButton.setOnClickListener {
            if (holder.quantityInput.isEnabled && item.quantity > 1) {
                item.quantity--
                holder.quantityInput.setText(item.quantity.toString())
                if (item.quantity == 1) {
                    holder.decreaseButton.isEnabled = false
                    holder.decreaseButton.setTextColor(0xFFA0A0A0.toInt())
                }
            }
        }

        holder.quantityInput.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val qty = s.toString().toIntOrNull()
                if (holder.quantityInput.isEnabled && qty != null && qty > 0) {
                    item.quantity = qty
                    if (qty == 1) {
                        holder.decreaseButton.isEnabled = false
                        holder.decreaseButton.setTextColor(0xFFA0A0A0.toInt())
                    } else {
                        holder.decreaseButton.isEnabled = true
                        holder.decreaseButton.setTextColor(0xFFB03A1E.toInt())
                    }
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    fun getSelectedItems():List<SideBeverageItem>{
        return itemList.filter { it.selectedSize != null && it.quantity > 0 }
    }
}
