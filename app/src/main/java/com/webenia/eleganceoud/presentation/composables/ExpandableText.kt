package com.webenia.eleganceoud.presentation.composables

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.webenia.eleganceoud.ui.theme.MidGrey
import com.webenia.eleganceoud.ui.theme.Primary

@Composable
fun ExpandableText(
    text: String,
    minimizedMaxLines: Int = 3,
    textColor: Color = MidGrey,
    fontSize: TextUnit = 16.sp
) {
    var isExpanded by remember { mutableStateOf(false) }
    var isTextOverflowing by remember { mutableStateOf(false) }

    // To measure if text overflows
    val textLayoutResult = remember { mutableStateOf<TextLayoutResult?>(null) }

    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = text,
            fontSize = fontSize,
            color = textColor,
            maxLines = if (isExpanded) Int.MAX_VALUE else minimizedMaxLines,
            overflow = TextOverflow.Ellipsis,
            softWrap = true,
            onTextLayout = { result: TextLayoutResult ->
                textLayoutResult.value = result
                isTextOverflowing = result.hasVisualOverflow
            },
            modifier = Modifier
                .animateContentSize() // smooth animation on expansion
        )

        if (isTextOverflowing || isExpanded) {
            Text(
                text = if (isExpanded) "See less" else "See more",
                color = Primary,
                fontSize = 14.sp,
                modifier = Modifier
                    .padding(top = 4.dp)
                    .clickable { isExpanded = !isExpanded }
            )
        }
    }
}
