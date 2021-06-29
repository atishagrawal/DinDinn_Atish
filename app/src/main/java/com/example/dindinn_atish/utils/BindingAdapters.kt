package com.example.dindinn_atish.utils

import android.text.TextUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.dindinn_atish.models.Data


/**
 * Created by Atish Agrawal on 15-05-2021.
 * Email: dir.atishagrawal@gmail.com
 * Phone: +91-9716987018
 */
class BindingAdapters {


    companion object {
        @BindingAdapter("createdAt")
        @JvmStatic
        fun setCreatedAt(textView: TextView, orderDetails: Data?) {
            if (orderDetails != null) {
                textView.text = DateTimeUtils().getTimeFromDateString(orderDetails.created_at)
            } else {
                textView.text = "Created Date Error"
            }

        }


        @BindingAdapter("initialTime")
        @JvmStatic
        fun setInitialTime(textView: TextView, orderDetails: Data?) {
            if (orderDetails != null) {
                textView.text = DateTimeUtils().getInitialTimeDifference(orderDetails.created_at, orderDetails.expired_at)
            } else {
                textView.text = "Remaining Time Error"
            }

        }

        @BindingAdapter("orderId")
        @JvmStatic
        fun setOrderId(textView: TextView, orderDetails: Data?) {
            if (orderDetails != null) {
                textView.text = "#${orderDetails.id}"
            } else {
                textView.text = "Invalid ID"
            }

        }

        @BindingAdapter("totalQty")
        @JvmStatic
        fun setTotalQty(textView: TextView, orderDetails: Data?) {
            if (orderDetails != null) {
                textView.text = "${orderDetails.quantity} Items"
            } else {
                textView.text = "0 Items"
            }

        }

        @BindingAdapter("orderQuantity")
        @JvmStatic
        fun setOrderQuantity(textView: TextView, orderDetails: Data?) {
            if (orderDetails != null) {
                textView.text = "x${orderDetails.quantity}"
            } else {
                textView.text = "x0"
            }

        }


        @BindingAdapter("ingredientImage")
        @JvmStatic
        fun setIngredientImage(imageView: ImageView, imageUrl: String?) {
            if (TextUtils.isEmpty(imageUrl)) {
                return
            } else {
                Glide.with(imageView.context).load(imageUrl).into(imageView);
            }

        }


    }
}