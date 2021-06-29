package com.example.dindinn_atish.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dindinn_atish.databinding.LayoutIngredientsPageBinding
import com.example.dindinn_atish.models.Category


/**
 * Created by Atish Agrawal on 29-06-2021.
 * Email: dir.atishagrawal@gmail.com
 * Phone: +91-9716987018
 */

class IngredientsPagerAdapter(private val categories: List<Category>) : RecyclerView.Adapter<IngredientsPagerAdapter
.DataViewHolder>() {

    inner class DataViewHolder(var binding: LayoutIngredientsPageBinding, var viewType: Int) : RecyclerView.ViewHolder(binding.root) {
        fun bind(singleCategory: Category) {

//            val linearLayoutManager = LinearLayoutManager(binding.root.context)
//            binding.recyclerViewIngredients.layoutManager = linearLayoutManager
            binding.recyclerViewIngredients.adapter = IngredientsItemsAdapter(singleCategory.ingredientsData)


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        return DataViewHolder(LayoutIngredientsPageBinding.inflate(LayoutInflater.from(parent.context), parent, false), viewType)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(categories[position])
    }

    override fun getItemCount(): Int = categories.size
}

