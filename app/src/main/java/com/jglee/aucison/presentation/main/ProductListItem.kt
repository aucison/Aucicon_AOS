package com.jglee.aucison.presentation.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.draw
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jglee.aucison.R
import com.jglee.aucison.data.main.ProductServiceResponse

@Composable
fun ProductItem(product: ProductServiceResponse.Product) {
    Column {
        Box(
            modifier = Modifier
                .size(150.dp)
                .background(Color.LightGray)
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = product.name,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            color = Color.Black,
            modifier = Modifier.widthIn(0.dp, 100.dp)
        )

        Spacer(modifier = Modifier.height(5.dp))

        val textStyleBody1 = MaterialTheme.typography.body1
        var textStyle by remember { mutableStateOf(textStyleBody1) }
        var readyToDraw by remember { mutableStateOf(false) }

        Text(
            text = product.summary,
            minLines = 1,
            maxLines = 2,
            fontSize = 10.sp,
            color = Color.Black,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.width(150.dp),
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = stringResource(R.string.product_price, product.price),
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            color = Color.Black,
            modifier = Modifier.widthIn(0.dp, 100.dp)
        )
    }
}