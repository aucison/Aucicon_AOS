package com.jglee.aucison.presentation.search

import androidx.lifecycle.ViewModel
import com.jglee.aucison.presentation.main.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull

class SearchViewModel: ViewModel() {
    val _searchResultInfo = MutableStateFlow<Pair<String, Int>?>(null)
    val searchResultInfo get() = _searchResultInfo.filterNotNull()

    private val _searchedItemList = MutableStateFlow<List<Product>>(listOf())
    val searchedItemList get() = _searchedItemList.filterNotNull()

    fun requestSearch(query: String) {
        _searchedItemList.value = listOf(
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

        _searchResultInfo.value = query to (_searchedItemList.value.size)
    }
}