package com.jglee.aucison.presentation.buy

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.jglee.aucison.R
import com.jglee.aucison.data.buy.ProductDetailResponse

@Composable
fun BuyProductPage(id: Int) {
    val viewModel = viewModel<BuyProductViewModel>().apply {
        requestProductDetail(0)
    }
    val productDetail = viewModel.productDetail.collectAsState(initial = ProductDetailResponse.mock)

    Column(verticalArrangement = Arrangement.spacedBy(20.dp)) {
        val info = productDetail.value ?: return@Column

        ProductImage(url = info.imgUrls.firstOrNull() ?: "")
        ProductSellInfo(info = info)
    }
}

@Composable
fun ProductImage(url: String) {
    val painter = if (url.isBlank()) {
        rememberAsyncImagePainter(url)
    } else {
        painterResource(id = R.drawable.temp)
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
fun ProductSellInfo(info: ProductDetailResponse.ProductDetailPayload) {
    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
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
            text = stringResource(id = R.string.product_price, info.price),
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
