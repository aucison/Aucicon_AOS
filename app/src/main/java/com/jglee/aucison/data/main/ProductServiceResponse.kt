package com.jglee.aucison.data.main

import java.io.Serializable

data class ProductServiceResponse(
    val products: List<Product> = listOf(),
    val message: String = "",
    val status: Int = 0,
) : Serializable {
    data class Product(
        val category: String = "",
        val createdAt: Long = 0L,
        val hw: String = "",
        val imgName: String = "",
        val path: String = "",
        val productsCode: String = "",
        val productsId: Long = 0L,
        val productsImgId: String = "",
        val size: String = "",
        val summary: String = "Thisisneverthat x New Era x MLB New York Yankees Unstructured Ball Cap White Black",
        val type: String = "",
        val url: String = "",
        val price: String = "53,000",
        val name: String = "디스이스네버댓"
    ) : Serializable
}