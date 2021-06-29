package com.example.dindinn_atish

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dindinn_atish.adapters.OrdersAdapter
import com.example.dindinn_atish.databinding.ActivityMainBinding
import com.example.dindinn_atish.models.Data
import com.example.dindinn_atish.viewmodel.OrdersViewModel
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {


    private val viewModel: OrdersViewModel by lazy { (ViewModelProvider(this)).get(OrdersViewModel::class.java) }

    private lateinit var binding: ActivityMainBinding

    private val ordersList = ArrayList<Data>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize Data Binding for the UI
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setContentView(binding.root)

        title = "Incoming"

        initViews()

        observeLiveData()

        fetchData()
    }


    private fun initViews() {
        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true)
        binding.recyclerviewOrders.layoutManager = linearLayoutManager
        binding.recyclerviewOrders.adapter = OrdersAdapter(ordersList)
    }


    private fun fetchData() {
        lifecycleScope.launch {
            viewModel.getOrders()
        }

    }


    private fun observeLiveData() {
        viewModel.orders.observe(this, Observer {
            ordersList.addAll(it.orderData)
            binding.recyclerviewOrders.adapter?.notifyDataSetChanged()
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.ingredientMenu -> {
                //Launch Ingredients Activity
                startActivity(Intent(this, IngredientsActivity::class.java))
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}