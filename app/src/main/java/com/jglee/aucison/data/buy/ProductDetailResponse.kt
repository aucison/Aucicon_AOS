package com.jglee.aucison.data.buy

data class ProductDetailResponse(
    val productDetail: ProductDetailPayload = ProductDetailPayload()
) {
    data class ProductDetailPayload(
        val brand: String = "",
        val name: String = "",
        val subName: String = "",
        val price: Int = 0,
        val isFavorite: Boolean = false,
        val deliverInfo: String = "",
        val options: List<String> = listOf(),
        val imgUrls: List<String> = listOf(),
    )

    companion object {
        val mock = ProductDetailPayload(
            brand = "Jordan",
            name = "조던 1 레트로 하이 OG 페이턴트 브레드",
            subName = "Jordon 1 Retro High OG Paitent Bred",
            price = 203000,
            isFavorite = false,
            deliverInfo = "2500원\n(도서, 산간 지역 4,500원)",
            options = listOf("250", "260", "270", "280", "290"),
            imgUrls = listOf("", "", "", "", "")
        )
    }
}
