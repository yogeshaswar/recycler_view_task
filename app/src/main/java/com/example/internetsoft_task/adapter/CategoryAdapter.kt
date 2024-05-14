package com.example.internetsoft_task.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.internetsoft_task.R
import com.example.internetsoft_task.contract.OnCategorySelectedListener
import com.example.internetsoft_task.model.Category

class CategoryAdapter(private val categories: List<Category>, private val listener: OnCategorySelectedListener) :
    RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    // state variable for clicked category
    private var selectedItemPosition: Int = RecyclerView.NO_POSITION

    inner class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val categoryName: TextView = itemView.findViewById(R.id.tvCategoryName)
        val categoryLayout: LinearLayout = itemView.findViewById(R.id.llCategoryItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.category_card, parent, false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = categories[position]
        holder.categoryName.text = category.name

        // Check if this item is selected or not
        if (position == selectedItemPosition) {
            holder.categoryLayout.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.orange))
        } else {
            holder.categoryLayout.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, android.R.color.black))
        }

        // Set OnClickListener to get selected category
        holder.categoryLayout.setOnClickListener {
            // Update selected item position
            val previousSelectedItemPosition = selectedItemPosition
            selectedItemPosition = holder.adapterPosition

            Log.d("CategoryAdapter", "Selected Category: ${category.name}")

            // Notify item changes for deselection of previously selected item
            notifyItemChanged(previousSelectedItemPosition)

            // Notify item changes for selection of newly clicked item
            notifyItemChanged(selectedItemPosition)

            // Notify the listener
            listener.onCategorySelected(category.name)
        }
    }

    override fun getItemCount(): Int {
        return categories.size
    }
}
