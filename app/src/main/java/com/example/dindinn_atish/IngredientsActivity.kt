package com.example.dindinn_atish

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.dindinn_atish.adapters.IngredientsPagerAdapter
import com.example.dindinn_atish.databinding.ActivityIngredientsBinding
import com.example.dindinn_atish.models.Category
import com.example.dindinn_atish.models.IngredientsAPI
import com.example.dindinn_atish.viewmodel.IngredientsViewModel
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.launch

class IngredientsActivity : AppCompatActivity() {

    private val viewModel: IngredientsViewModel by lazy { (ViewModelProvider(this)).get(IngredientsViewModel::class.java) }

    private lateinit var binding: ActivityIngredientsBinding


    val categoryList = ArrayList<Category>()
    var categoriesCount = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize Data Binding for the UI
        binding = DataBindingUtil.setContentView(this, R.layout.activity_ingredients)
        setContentView(binding.root)


        title = "Ingredients"


        initViews()

        observeLiveData()

        fetchData()
    }


    private fun initViews() {
        binding.pager.adapter = IngredientsPagerAdapter(categoryList)


    }


    private fun fetchData() {
        lifecycleScope.launch {
            viewModel.getAllIngredients()
        }

    }


    private fun observeLiveData() {
        viewModel.ingredients.observe(this, Observer {

            categoriesCount = it.category.size

            categoryList.addAll(it.category)
            binding.pager.adapter?.notifyDataSetChanged()
            loadTabs(it)


        })
    }

    private fun loadTabs(ingredientsAPI: IngredientsAPI) {
        TabLayoutMediator(binding.tabLayout, binding.pager) { tab, position ->
            tab.text = ingredientsAPI.category[position].title
        }.attach()

    }
}