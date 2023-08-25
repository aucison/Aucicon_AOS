package com.jglee.aucison.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp

@Composable
fun String.getBulletText(): AnnotatedString {
    val bullet = "\u2022"
    val paragraphStyle = ParagraphStyle(textIndent = TextIndent(restLine = 12.sp))

    return buildAnnotatedString {
        withStyle(style = paragraphStyle) {
            append(bullet)
            append("\t\t")
            append(this@getBulletText)
        }
    }
}