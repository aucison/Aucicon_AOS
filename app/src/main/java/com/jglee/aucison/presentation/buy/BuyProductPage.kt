package com.jglee.aucison.presentation.buy

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.jglee.aucison.R
import com.jglee.aucison.data.buy.ProductDetailResponse

@Composable
fun BuyProductPage(navController: NavController, id: Int) {
    val scrollState = rememberScrollState()
    val viewModel = viewModel<BuyProductViewModel>().apply {
        requestProductDetail(0)
    }
    val productDetail = viewModel.productDetail.collectAsState(initial = ProductDetailResponse.mock)

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(modifier = Modifier.weight(1f)) {
            Column(
                verticalArrangement = Arrangement.spacedBy(20.dp),
                modifier = Modifier.verticalScroll(scrollState)
            ) {
                val info = productDetail.value

                ProductImage(url = info.imgUrls.firstOrNull() ?: "")
                ProductSubImageList(info.imgUrls)
                ProductSellInfo(info = info)
            }
        }

        Text(
            text = stringResource(id = R.string.btn_buy_now),
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            color = Color.White,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .clip(shape = RoundedCornerShape(5.dp))
                .background(Color.Black)
                .padding(10.dp)
                .clickable { }
        )
    }
}

@Composable
fun ProductImage(url: String) {
    val painter = if (url.isBlank()) {
        painterResource(id = R.drawable.temp)
    } else {
        rememberAsyncImagePainter(url)
    }
    Image(
        painter = painter,
        contentDescription = null,
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
    )
}

@Composable
fun ProductSubImageList(urls: List<String>) {
    LazyRow(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        items(items = urls) { url ->
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .padding(horizontal = 5.dp)
            ) {
                ProductImage(url = url)
            }
        }
    }
}

@Composable
fun ProductSellInfo(info: ProductDetailResponse.ProductDetailPayload) {
    Column(verticalArrangement = Arrangement.spacedBy(10.dp), modifier = Modifier.padding(horizontal = 10.dp)) {
        Text(
            text = info.brand,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        )

        Column {
            Text(
                text = info.name,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = info.subName,
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal
            )
        }

        Text(
            text = stringResource(
                id = R.string.product_price,
                info.price
            ),
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )

        Image(
            painter = painterResource(id = R.drawable.ic_like),
            contentDescription = null,
            modifier = Modifier.size(15.dp)
        )
    }
}
