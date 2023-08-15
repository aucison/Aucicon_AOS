package com.jglee.aucison.presentation.main

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jglee.aucison.data.main.ItemType

@Composable
fun MainPage() {
    var selectedItemType by remember { mutableStateOf(ItemType.NORMAL) }
    val verticalScrollState = rememberScrollState()
    val viewModel = viewModel<MainViewModel>().also {
        requestBanners(it)
    }

    Column(
        Modifier.fillMaxWidth()
            .background(Color.White)
            .padding(horizontal = 10.dp)
    ) {

        MainItemTypeSelector(selectedItemType) { selectedItemType = it }

        Column(
            Modifier.weight(1f)
                .verticalScroll(verticalScrollState)
        ) {
            MainBanner()
            BestItemLayout(viewModel)
            NewItemLayout()
        }
    }

}

private fun requestBanners(viewModel: MainViewModel) {
    viewModel.requestMainBanner()
    viewModel.requestBestItemList()
    viewModel.requestNewItemList()
}

@Composable
fun MainItemTypeSelector(selected: ItemType, onClick: (ItemType) -> Unit) {
    val localDensity = LocalDensity.current
    var height by remember { mutableStateOf(0.dp) }

    Row(
        Modifier.padding(vertical = 5.dp)
            .fillMaxWidth(),
    ) {
        Button(
            { onClick(ItemType.NORMAL) },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
            border = BorderStroke(0.dp, Color.White),
            shape = RectangleShape,
            elevation = ButtonDefaults.elevation(0.dp),
            contentPadding = PaddingValues(vertical = 5.dp, horizontal = 10.dp)
        ) {
            Text(
                stringResource(ItemType.NORMAL.resource),
                color = Color.Black,
                fontSize = 16.sp,
                fontWeight = if (selected == ItemType.NORMAL) FontWeight.Bold else FontWeight.Normal,
                modifier = Modifier.onGloballyPositioned { coordinates ->
                    height = with(localDensity) { coordinates.size.height.toDp() }
                }
            )
        }

        Button(
            { onClick(ItemType.HANDMADE) },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
            border = BorderStroke(0.dp, Color.White),
            shape = RectangleShape,
            elevation = ButtonDefaults.elevation(0.dp),
            contentPadding = PaddingValues(vertical = 5.dp, horizontal = 10.dp)
        ) {
            Text(
                stringResource(ItemType.HANDMADE.resource),
                color = Color.Black,
                fontSize = 16.sp,
                fontWeight = if (selected == ItemType.HANDMADE) FontWeight.Bold else FontWeight.Normal
            )
        }
    }
}

@Composable
fun MainBanner() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(2.5738f)
//            .height(300.dp)
            .padding(bottom = 10.dp)
            .background(Color.LightGray),
        contentAlignment = Alignment.Center
    ) {
        Text("메인 배너 영역")
    }
}

@Composable
fun NewItemLayout() {
    Box(
        modifier = Modifier.fillMaxWidth()
            .height(150.dp)
            .padding(bottom = 10.dp)
            .background(Color.LightGray), contentAlignment = Alignment.Center
    ) {
        Text("뉴 아이템 영역")
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BestItemLayout(viewModel: MainViewModel) {
    val banners by viewModel.bestItemList.collectAsState(listOf())
    val lazyListState = rememberLazyListState()

    LazyRow(
        modifier = Modifier.fillMaxWidth()
            .height(150.dp)
            .padding(bottom = 10.dp),
        state = lazyListState,
        flingBehavior = rememberSnapFlingBehavior(lazyListState = lazyListState)
    ) {
        items(items = banners) { item ->
            Text(
                "베스트 아이템 영역",
                modifier = Modifier.fillParentMaxSize()
                    .background(item),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
@Preview
fun previewMainPage() {
    MainPage()
}