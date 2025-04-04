import android.graphics.Color
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.PizzaItem
import com.example.myapplication.R

class PizzaAdapter(private val pizzaList: List<PizzaItem>) :
    RecyclerView.Adapter<PizzaAdapter.PizzaViewHolder>() {

    inner class PizzaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.pizzaImage)
        val name: TextView = itemView.findViewById(R.id.pizzaName)
        val price: TextView = itemView.findViewById(R.id.pizzaPrice)
        val desc: TextView = itemView.findViewById(R.id.pizzaDescription)
        val checkbox: CheckBox = itemView.findViewById(R.id.pizzaCheckbox)
        val increase: TextView = itemView.findViewById(R.id.increaseButton)
        val decrease: TextView = itemView.findViewById(R.id.decreaseButton)
        val amount: EditText = itemView.findViewById(R.id.pizzaAmount)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PizzaViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_pizza_selection, parent, false)
        return PizzaViewHolder(view)
    }

    override fun getItemCount(): Int = pizzaList.size

    override fun onBindViewHolder(holder: PizzaViewHolder, position: Int) {
        val item = pizzaList[position]

        holder.name.text = item.name
        holder.price.text = "價格：$${item.price}"
        holder.desc.text = item.description
        holder.image.setImageResource(item.imageResId)
        holder.checkbox.isChecked = item.isChecked
        holder.amount.setText(item.quantity.toString())

        updateQuantityControls(holder, item.isChecked)

        holder.checkbox.setOnCheckedChangeListener { _, isChecked ->
            item.isChecked = isChecked
            updateQuantityControls(holder, isChecked)
        }

        holder.increase.setOnClickListener {
            if (item.isChecked) {
                item.quantity++
                holder.amount.setText(item.quantity.toString())
            }
        }

        holder.decrease.setOnClickListener {
            if (item.isChecked && item.quantity > 1) {
                item.quantity--
                holder.amount.setText(item.quantity.toString())
            }
        }

        holder.amount.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val num = s.toString().toIntOrNull()
                if (item.isChecked && num != null && num > 0) {
                    item.quantity = num
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    private fun updateQuantityControls(holder: PizzaViewHolder, enabled: Boolean) {
        val color = if (enabled) Color.BLACK else Color.parseColor("#A0A0A0")
        holder.amount.isEnabled = enabled
        holder.increase.isEnabled = enabled
        holder.decrease.isEnabled = enabled

        holder.amount.setTextColor(color)
        holder.increase.setTextColor(color)
        holder.decrease.setTextColor(color)
    }

    fun getSelectedPizzas(): List<PizzaItem> {
        return pizzaList.filter { it.isChecked }
    }
}
