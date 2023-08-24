package com.jglee.aucison.presentation.main

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jglee.aucison.R
import com.jglee.aucison.data.main.ProductServiceResponse
import com.jglee.aucison.data.main.SellType
import com.jglee.aucison.presentation.components.SellCategoryButton

@Composable
fun MainPage() {
    var selectedSellType by remember { mutableStateOf(SellType.AUCTION) }
    val verticalScrollState = rememberScrollState()
    val viewModel = viewModel<MainViewModel>().apply {
        requestMainBanner()
        requestBestItemList()
        requestNewItemList()
    }

    val mainBanners = viewModel.mainBannerList.collectAsState(listOf())
    val newItemList = viewModel.newItemList.collectAsState(listOf())
    val bestItemList = viewModel.bestItemList.collectAsState(listOf())

    Column(
        modifier = Modifier.fillMaxWidth()
            .background(Color.White)
            .verticalScroll(verticalScrollState),
//        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        MainBanner(mainBanners.value)
        MainSellTypeSelector(selectedSellType) { selectedSellType = it }
        BestItemLayout(bestItemList.value)
        NewItemLayout(newItemList.value)
    }

}

@Composable
fun MainSellTypeSelector(selected: SellType, onClick: (SellType) -> Unit) {
    Column(
        Modifier.fillMaxWidth(),
    ) {
        Row {
            SellCategoryButton(SellType.AUCTION, selected, onClick)
            SellCategoryButton(SellType.NORMAL, selected, onClick)
        }

        Box(
            modifier = Modifier.fillMaxWidth()
                .height(1.dp)
                .background(Color.LightGray)
                .padding(horizontal = 10.dp)
        )
    }
}

@Composable
fun MainBanner(mainBanner: List<Color>) {
    AutoLoopBanner(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1.5f)
//            .height(300.dp)
            .padding(bottom = 10.dp)
            .background(Color.LightGray),
        list = mainBanner
    )
}

@Composable
fun NewItemLayout(banners: List<ProductServiceResponse.Product>) {
    MainItemBanner(
        banners = banners,
        title = stringResource(R.string.item_category_new_item),
        desc = stringResource(R.string.item_category_new_item_desc)
    )
}

@Composable
fun BestItemLayout(banners: List<ProductServiceResponse.Product>) {
    MainItemBanner(
        banners = banners,
        title = stringResource(R.string.item_category_best_item),
        desc = stringResource(R.string.item_category_best_item_desc)
    )
}

@Composable
@OptIn(ExperimentalFoundationApi::class)
fun MainItemBanner(banners: List<ProductServiceResponse.Product>, title: String, desc: String) {
    val lazyListState = rememberLazyListState()

    Column {
        Text(
            text = title,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(top = 20.dp, start = 10.dp)
        )
        Text(
            text = desc,
            fontSize = 10.sp,
            color = Color.LightGray,
            modifier = Modifier.padding(horizontal = 10.dp)
        )

        LazyRow(
            modifier = Modifier.fillMaxWidth()
                .padding(vertical = 10.dp),
            state = lazyListState,
            flingBehavior = rememberSnapFlingBehavior(lazyListState = lazyListState),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(items = banners) { item ->
                ProductItem(item)
            }
        }
    }
}

@Composable
@Preview
fun previewMainPage() {
    MainPage()
}