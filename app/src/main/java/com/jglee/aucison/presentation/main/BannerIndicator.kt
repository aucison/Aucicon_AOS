package com.jglee.aucison.presentation.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun BannerIndicator(modifier: Modifier, count: Int, currentPage: Int) {
    Row(modifier = modifier, horizontalArrangement = Arrangement.spacedBy(10.dp)) {
        repeat(count) {
            val isSelected = it == currentPage
            val size = if (isSelected) 10.dp else 8.dp
            val color = if (isSelected) Color.White else Color.LightGray

            Box(
                modifier = Modifier
                    .size(size)
                    .background(color = color, shape = CircleShape)
                    .align(Alignment.CenterVertically)
                ,
            )
        }
    }
}