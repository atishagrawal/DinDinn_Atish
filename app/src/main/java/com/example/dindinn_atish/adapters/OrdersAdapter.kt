package com.example.dindinn_atish.adapters

import android.media.Ringtone
import android.media.RingtoneManager
import android.net.Uri
import android.os.CountDownTimer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dindinn_atish.databinding.LayoutOrderItemBinding
import com.example.dindinn_atish.models.Data
import com.example.dindinn_atish.utils.DateTimeUtils


/**
 * Created by Atish Agrawal on 27-06-2021.
 * Email: dir.atishagrawal@gmail.com
 * Phone: +91-9716987018
 */
class OrdersAdapter(private val ordersList: ArrayList<Data>) : RecyclerView.Adapter<OrdersAdapter
.DataViewHolder>() {

    private val TAG = OrdersAdapter::class.java.simpleName

    inner class DataViewHolder(var binding: LayoutOrderItemBinding, var viewType: Int) : RecyclerView.ViewHolder(binding.root) {


        var timer: OrderCountdownTimer? = null
        var ringtoneManager: Ringtone? = null
        var cancelTimer: Boolean = false

        fun bind(singleOrder: Data, position: Int) {
            binding.orderDetailsModel = singleOrder
            binding.showAccept = !DateTimeUtils().isTimeExpired(singleOrder.created_at, singleOrder.expired_at)

            val linearLayoutManager = LinearLayoutManager(binding.root.context)
            binding.recyclerviewOrderAddOnItems.layoutManager = linearLayoutManager
            binding.recyclerviewOrderAddOnItems.adapter = AddOnAdapter(singleOrder.addon)


            binding.btnAccept.setOnClickListener(View.OnClickListener {
                cancelTimer = true
                timer!!.cancel()
                Toast.makeText(binding.root.context, "Order #${singleOrder.id} Accepted", Toast.LENGTH_SHORT).show()
                this.ringtoneManager?.stop()
                deleteCurrentOrderCard(position)
            })


            binding.btnExpired.setOnClickListener(View.OnClickListener {
                Toast.makeText(binding.root.context, "Order Removed", Toast.LENGTH_SHORT).show()
                deleteCurrentOrderCard(position)
            })


        }


    }


    private fun deleteCurrentOrderCard(position: Int) {
        if (ordersList.size <= 0) return
        ordersList.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, ordersList.size);
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        return DataViewHolder(LayoutOrderItemBinding.inflate(LayoutInflater.from(parent.context), parent, false), viewType)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {

        val currentOrder = ordersList[position]

        holder.bind(currentOrder, position)

        val timeRemaining = DateTimeUtils().getTimeDifference(currentOrder.created_at, currentOrder.expired_at)
        val timeToAlert = DateTimeUtils().getTimeDifference(currentOrder.created_at, currentOrder.alerted_at)

        if (holder.timer != null) {
            holder.timer!!.cancel()
        }


        holder.timer = OrderCountdownTimer(timeRemaining, 1000, holder, currentOrder)
        holder.timer!!.start()

    }

    override fun getItemCount(): Int = ordersList.size


    inner class OrderCountdownTimer(millis: Long, interval: Long = 1000, val holder: DataViewHolder, val currentOrder: Data) :
        CountDownTimer(millis, interval) {


        override fun onTick(millisUntilFinished: Long) {

            if (holder.cancelTimer) cancel()


            val timeRemaining = DateTimeUtils().getTimeDifference(currentOrder.created_at, currentOrder.expired_at)
            val timeToAlert = DateTimeUtils().getTimeDifference(currentOrder.created_at, currentOrder.alerted_at)

            val timeOutSeconds = timeRemaining / 1000
            val alertSeconds = timeToAlert / 1000

            Log.d("OrderCountdownTimer", "${currentOrder.id} Tick")

            val currentSecond: Long = millisUntilFinished / 1000
            holder.binding.textViewOrderTimeOut.text = DateTimeUtils().getFormattedTime(millisUntilFinished)

            if (currentSecond == 0L) {
                holder.binding.progressbarTimeOut.progress = 0
            } else {
                val progress: Float = (((millisUntilFinished * 100) / timeRemaining)).toFloat()
                with(holder.binding.progressbarTimeOut) {
                    this.progress = progress.toInt()
                }
            }


            //Check if the current time is to alert
            if (timeOutSeconds - alertSeconds == currentSecond) {
                //Play Sound. Alert
                try {
                    val notification: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE)
                    holder.ringtoneManager = RingtoneManager.getRingtone(holder.binding.root.context, notification)
                    with(holder) {
                        if (!holder.cancelTimer) {
                            Log.d(TAG, "Playing RingTone")
                            ringtoneManager?.play()
                        }
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }
        }

        override fun onFinish() {
            holder.binding.textViewOrderTimeOut.text = "0s"
            holder.binding.showAccept = false
        }

    }

}

