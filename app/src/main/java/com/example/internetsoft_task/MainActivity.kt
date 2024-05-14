package com.example.internetsoft_task

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.internetsoft_task.adapter.CategoryAdapter
import com.example.internetsoft_task.contract.OnCategorySelectedListener
import com.example.internetsoft_task.model.Category

class MainActivity : AppCompatActivity(), OnCategorySelectedListener {
    lateinit var rvCategory: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // list of ecommerce categories
        val categories = listOf(
            Category("Electronics"),
            Category("Clothing"),
            Category("Home & Kitchen"),
            Category("Beauty & Personal Care"),
            Category("Books & Media"),
            Category("Sports & Outdoors"),
            Category("Toys & Games"),
            Category("Health & Wellness"),
            Category("Automotive"),
            Category("Furniture & Decor")
        )

        rvCategory = findViewById(R.id.recyclerViewCategory)
        rvCategory.layoutManager = GridLayoutManager(this, 2)
        rvCategory.adapter = CategoryAdapter(categories, this)

    }

    override fun onCategorySelected(categoryName: String) {
        Toast.makeText(this, "Selected Category: $categoryName", Toast.LENGTH_SHORT).show()
    }
}