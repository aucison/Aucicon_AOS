package com.jglee.aucison.presentation.market

import androidx.lifecycle.ViewModel
import com.jglee.aucison.presentation.main.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull

class MarketViewModel: ViewModel() {
    private val _marketItems = MutableStateFlow<List<Product>?>(null)
    val marketItems get() = _marketItems.filterNotNull()

    fun requestMarketItems() {
        _marketItems.value = listOf(
            Product(),
            Product(),
            Product(),
            Product(),
            Product(),
            Product(),
            Product(),
            Product(),
            Product(),
            Product(),
            Product(),
            Product(),
            Product(),
            Product(),
            Product(),
            Product(),
            Product(),
            Product(),
            Product(),
            Product(),
        )
    }

}