package com.jglee.aucison.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jglee.aucison.data.main.SellType

@Composable
fun SellCategoryButton(type: SellType, selected: SellType, onClick: (SellType) -> Unit) {
    val localDensity = LocalDensity.current
    var height by remember { mutableStateOf(0.dp) }
    Text(
        stringResource(type.resource),
        color = Color.Black,
        fontSize = 16.sp,
        fontWeight = if (type == selected) FontWeight.Bold else FontWeight.Medium,
        modifier = Modifier.onGloballyPositioned { coordinates ->
            height = with(localDensity) { coordinates.size.height.toDp() }
        }
            .clickable { onClick(type) }
            .padding(10.dp)
    )
}