package com.jglee.aucison.presentation.search

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.substring
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jglee.aucison.R
import com.jglee.aucison.presentation.components.ProductGrid
import com.jglee.aucison.presentation.main.Product

@Composable
fun SearchPage(query: String) {
    val viewModel = viewModel<SearchViewModel>().apply {
        requestSearch(query)
    }

    val searchedInfo = viewModel.searchResultInfo.collectAsState(initial = query to 0)
    val searchedItemList = viewModel.searchedItemList.collectAsState(initial = listOf())

    Column(modifier = Modifier.padding(10.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
        SearchResultInfo(searchedInfo.value)
        SearchResultItemList(searchedItemList.value)
    }
}

@Composable
fun SearchResultInfo(searchedInfo: Pair<String, Int>) {
    val (query, count) = searchedInfo

    Text(
        text = buildAnnotatedString {
            withStyle(
                SpanStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            ) {
                append(
                    stringResource(
                        id = R.string.search_result_query,
                        query
                    )
                )
            }
            append(
                stringResource(
                    id = R.string.search_result_desc,
                    count
                )
            )
        },
        fontSize = 14.sp,
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(5.dp))
            .background(Color.LightGray)
            .padding(10.dp)

    )
}

@Composable
fun SearchResultItemList(list: List<Product>) {
    ProductGrid(itemList = list)
}