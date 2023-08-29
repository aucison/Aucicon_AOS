package com.jglee.aucison.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.jglee.aucison.R

@Composable
fun AcoToggleButton(
    modifier: Modifier = Modifier,
    onToggle: (Boolean) -> Unit = {},
) {
    var isSelected by remember { mutableStateOf(false) }

    val iconPaint = painterResource(
        if (isSelected) R.drawable.ic_toggle_off
        else R.drawable.ic_toggle_on
    )

    Image(
        painter = iconPaint,
        contentDescription = null,
        modifier = Modifier.clickable {
            isSelected = isSelected.not()
            onToggle(isSelected)
        }.then(modifier)
    )
}