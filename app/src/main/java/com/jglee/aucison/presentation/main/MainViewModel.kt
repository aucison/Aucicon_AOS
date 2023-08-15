package com.jglee.aucison.presentation.main

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull

class MainViewModel: ViewModel() {
    var _bestItemList = MutableStateFlow<List<Color>?>(null)
    val bestItemList get() = _bestItemList.filterNotNull()

    private var _mainBannerList = MutableStateFlow<List<Color>?>(null)
    val mainBannerList get() = _mainBannerList.filterNotNull()

    private var _newItemList = MutableStateFlow<List<Color>?>(null)
    val newItemList get() = _newItemList.filterNotNull()

    fun requestBestItemList() {
        _bestItemList.value = listOf(Color.Blue, Color.Red, Color.Green)
    }

    fun requestMainBanner() {
        _mainBannerList.value = listOf(Color.Blue, Color.Red, Color.Green)
    }

    fun requestNewItemList() {
        _newItemList.value = listOf(Color.Blue, Color.Red, Color.Green)
    }
}