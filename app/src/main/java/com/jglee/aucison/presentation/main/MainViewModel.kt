package com.jglee.aucison.presentation.main

import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.jglee.aucison.data.main.ProductServiceResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull

typealias Product = ProductServiceResponse.Product

class MainViewModel: ViewModel() {
    var _bestItemList = MutableStateFlow<List<Product>?>(null)
    val bestItemList get() = _bestItemList.filterNotNull()

    private var _mainBannerList = MutableStateFlow<List<Color>?>(null)
    val mainBannerList get() = _mainBannerList.filterNotNull()

    private var _newItemList = MutableStateFlow<List<Product>?>(null)
    val newItemList get() = _newItemList.filterNotNull()

    fun requestBestItemList() {
        _bestItemList.value = listOf(Product(), Product(), Product(), Product(), Product(), Product())
    }

    fun requestMainBanner() {
        _mainBannerList.value = listOf(Color.Blue, Color.Red, Color.Green)
    }

    fun requestNewItemList() {
        _newItemList.value = listOf(Product(), Product(), Product(), Product(), Product(), Product())
    }
}