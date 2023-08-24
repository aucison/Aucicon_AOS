package com.jglee.aucison.presentation.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jglee.aucison.R

@Composable
fun SearchPage(query: String) {
    val viewModel = viewModel<SearchViewModel>().apply {
        requestSearch(query)
    }

    val searchedInfo = viewModel.searchResultInfo.collectAsState(initial = "" to 0)
    val searchedItemList = viewModel.searchedItemList.collectAsState(initial = listOf())

    Column(modifier = Modifier.padding(10.dp)) {
        SearchResultInfo(
            searchedInfo.value.first,
            searchedInfo.value.second
        )
    }
}

@Composable
fun SearchResultInfo(query: String, count: Int) {
    Text(
        text = buildAnnotatedString {
            stringResource(id = R.string.search_result_desc, query, count)
                .split("\'$query\'")
                .also { originTexts ->
                    withStyle(
                        SpanStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    ) {
                        append(originTexts.first())
                    }
                    append(originTexts.last())
                }
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .background(Color.LightGray)
    )
}