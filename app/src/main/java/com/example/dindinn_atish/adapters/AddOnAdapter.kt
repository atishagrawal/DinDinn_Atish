package com.example.dindinn_atish.adapters

import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dindinn_atish.databinding.LayoutInnerOrderItemsBinding
import com.example.dindinn_atish.databinding.LayoutOrderItemBinding
import com.example.dindinn_atish.models.Addon
import com.example.dindinn_atish.models.Data
import com.example.dindinn_atish.utils.DateTimeUtils
import com.example.dindinn_atish.viewmodel.OrdersViewModel


/**
 * Created by Atish Agrawal on 27-06-2021.
 * Email: dir.atishagrawal@gmail.com
 * Phone: +91-9716987018
 */
class AddOnAdapter(private val addOns: List<Addon>) : RecyclerView.Adapter<AddOnAdapter
.DataViewHolder>() {

    inner class DataViewHolder(var binding: LayoutInnerOrderItemsBinding, var viewType: Int) : RecyclerView.ViewHolder(binding.root) {
        fun bind(singleAddOn: Addon) {
            binding.addOnModel = singleAddOn
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        return DataViewHolder(LayoutInnerOrderItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false), viewType)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(addOns[position])
    }

    override fun getItemCount(): Int = addOns.size
}

