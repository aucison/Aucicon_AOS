package com.jglee.aucison.presentation.buy

import androidx.lifecycle.ViewModel
import com.jglee.aucison.data.buy.ProductDetailResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull

class BuyProductViewModel: ViewModel() {

    private val _productDetail = MutableStateFlow<ProductDetailResponse.ProductDetailPayload?>(null)
    val productDetail get() = _productDetail.filterNotNull()


    fun requestProductDetail(id: Int) {
        _productDetail.value = ProductDetailResponse.mock
    }
}