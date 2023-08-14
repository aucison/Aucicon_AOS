package com.jglee.aucison.presentation.main

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {

    fun requestBanner(): List<Color> {
        return listOf(Color.Blue, Color.Red, Color.Green)
    }
}