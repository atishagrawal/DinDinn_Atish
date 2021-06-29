package com.example.dindinn_atish.models

data class Data(
    val addon: List<Addon>,
    val alerted_at: String,
    val created_at: String,
    val expired_at: String,
    val id: Int,
    val quantity: Int,
    val title: String
)