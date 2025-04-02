import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.PizzaItem
import com.example.myapplication.R

class PizzaAdapter(private val pizzaList: List<PizzaItem>) :
    RecyclerView.Adapter<PizzaAdapter.PizzaViewHolder>() {

    class PizzaViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.pizzaImage)
        val name: TextView = view.findViewById(R.id.pizzaName)
        val price: TextView = view.findViewById(R.id.pizzaPrice)
        val description: TextView = view.findViewById(R.id.pizzaDescription)
        val radioButton: RadioButton = view.findViewById(R.id.pizzaRadio)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PizzaViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_pizza_selection, parent, false)
        return PizzaViewHolder(view)
    }

    override fun onBindViewHolder(holder: PizzaViewHolder, position: Int) {
        val pizza = pizzaList[position]
        holder.image.setImageResource(pizza.imageRes)
        holder.name.text = pizza.name
        holder.price.text = "價格：$${pizza.price}"
        holder.description.text = pizza.description
    }

    override fun getItemCount(): Int = pizzaList.size
}
