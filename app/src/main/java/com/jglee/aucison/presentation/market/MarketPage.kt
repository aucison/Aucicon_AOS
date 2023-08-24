package com.jglee.aucison.presentation.market

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jglee.aucison.R
import com.jglee.aucison.data.main.SellType
import com.jglee.aucison.presentation.components.SellCategoryButton
import com.jglee.aucison.presentation.main.Product
import com.jglee.aucison.presentation.main.ProductItem

@Composable
fun MarketPage() {
    val viewModel = viewModel<MarketViewModel>().apply { 
        requestMarketItems()
    }
    val marketItems = viewModel.marketItems.collectAsState(initial = listOf())

    Column {
        MarketCategoryTab()
        GridMarketItemList(itemList = marketItems.value)
    }
}

@Composable
fun MarketCategoryTab() {
    val sellTypes = listOf(SellType.NORMAL, SellType.HANDMADE)
    val auctionTypes = listOf(SellType.AUCTION, SellType.NON_AUCTION)

    var selectedSellType by remember { mutableStateOf(sellTypes.first()) }
    var selectedAuctionType by remember { mutableStateOf(auctionTypes.first()) }

    Column {
        Text(
            text = stringResource(id = R.string.market_category),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 10.dp)
        )

        Row {
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                sellTypes.forEach { type ->
                    SellCategoryButton(
                        type = type,
                        selected = selectedSellType,
                        onClick = { selectedSellType = type }
                    )
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                auctionTypes.forEach { type ->
                    SellCategoryButton(
                        type = type,
                        selected = selectedAuctionType,
                        onClick = { selectedAuctionType = type }
                    )
                }
            }
        }
    }
}

@Composable
fun GridMarketItemList(itemList: List<Product>) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(150.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(itemList) { item ->
            ProductItem(product = item)
        }
    }
}