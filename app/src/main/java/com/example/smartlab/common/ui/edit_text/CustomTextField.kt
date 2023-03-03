package com.example.smartlab.common.ui.edit_text

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smartlab.common.ui.click.alphaClick
import com.example.smartlab.common.ui.theme.colorDescription
import com.example.smartlab.common.ui.theme.lightGray
import com.example.smartlab.common.ui.theme.sfProDisplayFontFamily


@Composable
internal fun CustomTextField(
    text: String,
    onTextChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    labelTop: String? = null,
    hint: String? = null,
    singleLine: Boolean = true,
    readOnly: Boolean = false,
    @DrawableRes icon: Int? = null,
    @DrawableRes iconAction: Int? = null,
    action: (() -> Unit)? = null,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        labelTop?.let { label ->
            Text(
                text = label,
                fontFamily = sfProDisplayFontFamily,
                fontSize = 14.sp,
                fontWeight = FontWeight.W400,
                color = MaterialTheme.colors.colorDescription
            )
        }
        BasicTextField(
            value = text,
            onValueChange = onTextChange,
            textStyle = TextStyle(
                fontSize = 15.sp,
                fontFamily = sfProDisplayFontFamily,
                fontWeight = FontWeight.W400,
                color = Color.Black
            ),
            singleLine = singleLine,
            readOnly = readOnly,
            enabled = !readOnly,
            modifier = modifier
                .clip(MaterialTheme.shapes.medium)
                .fillMaxWidth()
                .background(MaterialTheme.colors.surface, MaterialTheme.shapes.medium)
                .border(1.dp, lightGray, MaterialTheme.shapes.medium)
                .padding(14.dp),
        ) { innerTextField ->
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                icon?.let {
                    Icon(
                        painter = painterResource(it),
                        contentDescription = null,
                        tint = MaterialTheme.colors.colorDescription,
                        modifier = Modifier.size(20.dp)
                    )
                }
                Box(
                    Modifier.weight(1f),
                    contentAlignment = Alignment.CenterStart
                ) {
                    if (text.isEmpty() && hint != null) {
                        Text(
                            text = hint,
                            fontFamily = sfProDisplayFontFamily,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.W400,
                            color = MaterialTheme.colors.colorDescription,
                        )
                    }
                    innerTextField()
                }
                iconAction?.let {
                    action ?: return@let
                    Icon(
                        painter = painterResource(it),
                        contentDescription = null,
                        tint = MaterialTheme.colors.colorDescription,
                        modifier = Modifier
                            .size(20.dp)
                            .alphaClick(action)
                    )
                }
            }
        }
    }
}