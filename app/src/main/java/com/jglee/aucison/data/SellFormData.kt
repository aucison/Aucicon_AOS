package com.jglee.aucison.data

data class SellFormData(
    val title: String = "",
    val productDescription: String = "",
    val images: List<String> = listOf(),
    val auctionType: Boolean = false,
    val minPrice: String = "",
    val startPrice: String = "",
    val auctionPrice: String = ""
)
