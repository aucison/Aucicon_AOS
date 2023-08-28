package com.jglee.aucison.presentation.sell

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.jglee.aucison.R
import com.jglee.aucison.data.main.SellType
import com.jglee.aucison.presentation.components.getBulletText
import com.jglee.aucison.ui.theme.ButtonUnselected
import com.jglee.aucison.ui.theme.CautionColor
import com.jglee.aucison.ui.theme.LightGray

@Composable
fun SellPage(navController: NavController) {
    val scrollState = rememberScrollState()
    val viewModel = viewModel<SellViewModel>()

    Column(
        verticalArrangement = Arrangement.spacedBy(20.dp),
        modifier = Modifier
            .verticalScroll(scrollState)
    ) {
        SellCaution()
        SellForm()
    }
}

@Composable
private fun SellCaution() {
    Column(
        modifier = Modifier
            .padding(top = 10.dp, start = 10.dp, end = 10.dp)
            .background(
                color = LightGray,
                shape = RoundedCornerShape(10.dp)
            )
            .padding(10.dp)
    ) {
        Text(
            text = stringResource(id = R.string.caution),
            color = CautionColor,
            fontSize = 16.sp,
            modifier = Modifier.padding(5.dp)
        )

        Text(
            text = stringResource(id = R.string.caution_sell_1).getBulletText(),
            color = CautionColor,
            fontSize = 14.sp,
        )

        Text(
            text = stringResource(id = R.string.caution_sell_2).getBulletText(),
            color = CautionColor,
            fontSize = 14.sp,
        )
    }
}

@Composable
private fun SellForm() {
    var productTitle by remember { mutableStateOf("") }
    var productDescription by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var auctionPrice by remember { mutableStateOf("") }

    Column(verticalArrangement = Arrangement.spacedBy(10.dp), modifier = Modifier.padding(10.dp)) {
        SellCategory()
        SellInputField(
            label = stringResource(id = R.string.title),
            text = productTitle,
            placeHolder = stringResource(id = R.string.title),
            singleLine = true,
            onValueChange = { productTitle = it }
        )
        SellInputField(
            label = stringResource(id = R.string.product_info),
            text = productDescription,
            placeHolder = stringResource(id = R.string.product_info_desc),
            singleLine = false,
            modifier = Modifier.fillMaxWidth().aspectRatio(1f),
            onValueChange = { productDescription = it }
        )
        SellInputField(
            label = stringResource(id = R.string.min_price),
            text = price,
            placeHolder = stringResource(id = R.string.min_price_desc),
            singleLine = true,
            onValueChange = { price = it }
        )
        SellInputField(
            label = stringResource(id = R.string.auction_price),
            text = auctionPrice,
            placeHolder = stringResource(id = R.string.auction_price_desc),
            singleLine = true,
            onValueChange = { auctionPrice = it }
        )
    }
}

@Composable
private fun SellLabel(text: String) {
    Text(
        text = text,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        color = Color.Black,
        modifier = Modifier.width(80.dp),
        textAlign = TextAlign.Start
    )
}

@Composable
private fun SellCategory() {
    val categoryList = listOf(
        SellType.NORMAL,
        SellType.HANDMADE
    )
    var selected by remember { mutableStateOf((categoryList.first())) }

    Row(verticalAlignment = Alignment.CenterVertically) {
        SellLabel(text = stringResource(R.string.market_category))

        categoryList.forEach { category ->
            SellFormButton(
                selected = selected,
                category = category
            ) {
                selected = category
            }

            Spacer(Modifier.width(10.dp))
        }

    }
}

@Composable
private fun SellInputField(
    label: String,
    text: String,
    placeHolder: String,
    singleLine: Boolean,
    modifier: Modifier? = null,
    onValueChange: (String) -> Unit,
) {
    Row {
        SellLabel(text = label)

        val fieldModifier = Modifier
            .weight(1f)
            .border(
                width = 1.dp,
                color = ButtonUnselected,
                shape = RoundedCornerShape(5.dp)
            )

        BasicTextField(
            value = text,
            onValueChange = onValueChange,
            textStyle = TextStyle(
                color = Color.Black,
                fontSize = 14.sp
            ),
            singleLine = singleLine,
            modifier = modifier?.let { fieldModifier.then(it) } ?: fieldModifier,
            decorationBox = { innerTextField ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp)
                ) {
                    if (text.isEmpty()) {
                        Text(
                            text = placeHolder,
                            color = ButtonUnselected,
                            fontSize = 14.sp
                        )
                    }

                    innerTextField()
                }
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done)
        )
    }
}

@Composable
private fun SellFormButton(selected: SellType, category: SellType, onClick: () -> Unit) {
    val isSelected = (selected == category)
    val textColor = if (isSelected) Color.White else ButtonUnselected
    val backColor = if (isSelected) Color.Black else Color.White
    val borderColor = if (isSelected) Color.Black else ButtonUnselected

    Text(
        text = stringResource(id = category.resource),
        color = textColor,
        modifier = Modifier
            .border(
                width = 1.dp,
                color = borderColor,
                shape = RoundedCornerShape(5.dp)
            )
            .clip(shape = RoundedCornerShape(5.dp))
            .background(color = backColor)
            .clickable(onClick = onClick)
            .padding(
                vertical = 5.dp,
                horizontal = 15.dp
            )
    )
}