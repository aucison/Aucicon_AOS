package com.jglee.aucison.presentation.main

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

private const val INTERVAL_LOOPING = 5_000L
private const val MAX_PAGE = 1_000

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AutoLoopBanner(
    modifier: Modifier = Modifier,
    list: List<Color>
) {
    if (list.isEmpty()) return

    val pagerState = rememberPagerState(
        initialPage = MAX_PAGE,
        initialPageOffsetFraction = 0f
    ) { MAX_PAGE }

    LaunchedEffect(key1 = Unit) {
        pagerState.scrollToPage(0)
    }

    LaunchedEffect(key1 = pagerState.currentPage) {
        delay(INTERVAL_LOOPING)
        withContext(NonCancellable) {
            val nextPage = pagerState.currentPage + 1
            pagerState.animateScrollToPage(nextPage.takeIf { it <= pagerState.pageCount } ?: 0)
        }
    }

    Box(modifier = modifier) {
        HorizontalPager(
            state = pagerState
        ) {
            val index = it % (list.size)
            val item = list.getOrNull(index) ?: return@HorizontalPager

            Spacer(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = item)
            )
        }

        BannerIndicator(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(5.dp)
            ,
            count = list.size,
            currentPage = pagerState.currentPage % (list.size))
    }
}