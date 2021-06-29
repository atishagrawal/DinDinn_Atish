package com.example.dindinn_atish.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dindinn_atish.databinding.LayoutIngredientItemBinding
import com.example.dindinn_atish.models.IngredientsData


/**
 * Created by Atish Agrawal on 27-06-2021.
 * Email: dir.atishagrawal@gmail.com
 * Phone: +91-9716987018
 */
class IngredientsItemsAdapter(private val ingredients: List<IngredientsData>) : RecyclerView.Adapter<IngredientsItemsAdapter
.DataViewHolder>() {

    inner class DataViewHolder(var binding: LayoutIngredientItemBinding, var viewType: Int) : RecyclerView.ViewHolder(binding.root) {
        fun bind(singleIngredient: IngredientsData) {
            binding.ingredientModel = singleIngredient
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        return DataViewHolder(LayoutIngredientItemBinding.inflate(LayoutInflater.from(parent.context), parent, false), viewType)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(ingredients[position])
    }

    override fun getItemCount(): Int = ingredients.size
}

