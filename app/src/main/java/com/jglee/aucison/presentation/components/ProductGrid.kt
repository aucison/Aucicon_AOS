package com.jglee.aucison.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jglee.aucison.presentation.main.Product
import com.jglee.aucison.presentation.main.ProductItem

@Composable
fun GridProductItem(item: Product, onClick: (Int) -> Unit) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.padding(bottom = 10.dp)
    ) {
        ProductItem(product = item, onClick)
    }
}

@Composable
fun ProductGrid(itemList: List<Product>, onClick: (Int) -> Unit) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(150.dp),
        modifier = Modifier.fillMaxWidth(),
    ) {
        items(itemList) { item ->
            GridProductItem(item = item, onClick)
        }
    }
}