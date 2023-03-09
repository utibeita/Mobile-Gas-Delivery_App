package com.example.mystore.data.models

data class Product(
    var id: String? = null,
    var image: String? = null,
    var address: String? = null,
    var price: Double = 0.0,
    var seller:String? = null,
    var description: String? = null
)
