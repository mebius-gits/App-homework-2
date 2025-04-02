package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BeverageAdapter(private val itemList: List<SideBeverageItem>) :
    RecyclerView.Adapter<BeverageAdapter.BeverageViewHolder>() {

    // ViewHolder that represents an individual item
    class BeverageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.sideBeverageImage)
        val nameTextView: TextView = view.findViewById(R.id.sideBeverageName)
        val largePriceTextView: TextView = view.findViewById(R.id.largePrice)
        val mediumPriceTextView: TextView = view.findViewById(R.id.mediumPrice)
        val smallPriceTextView: TextView = view.findViewById(R.id.smallPrice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeverageViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_side_beverage_selection, parent, false)
        return BeverageViewHolder(view)
    }

    override fun onBindViewHolder(holder: BeverageViewHolder, position: Int) {
        val currentItem = itemList[position]
        holder.imageView.setImageResource(currentItem.imageResId)
        holder.nameTextView.text = currentItem.name
        holder.largePriceTextView.text = "大: \$${currentItem.largePrice}"
        holder.mediumPriceTextView.text = "中小: \$${currentItem.mediumPrice}"
        holder.smallPriceTextView.text = "Small: \$${currentItem.smallPrice}"
    }

    override fun getItemCount() = itemList.size
}
