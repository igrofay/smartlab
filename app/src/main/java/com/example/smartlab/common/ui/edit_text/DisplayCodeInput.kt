package com.example.smartlab.common.ui.edit_text

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smartlab.common.ui.theme.lightGray
import com.example.smartlab.common.ui.theme.sfProDisplayFontFamily
import kotlinx.coroutines.delay

@OptIn(ExperimentalComposeUiApi::class)
@Composable
internal fun DisplayCodeInput(
    value:String,
    onValueChange: (String)-> Unit,
    count: Int,
) {
    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current
    LaunchedEffect(Unit){
        focusRequester.requestFocus()
        keyboardController?.show()
    }
    Box(
        modifier = Modifier.clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = null,
            onClick = {
                focusRequester.requestFocus()
                keyboardController?.show()
            },
        )
    ){
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .size(1.dp)
                .alpha(0f)
                .focusRequester(focusRequester),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.None
            ),
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ){
            for (i in 0 until count){
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .background(MaterialTheme.colors.surface, MaterialTheme.shapes.medium)
                        .border(1.dp, lightGray, MaterialTheme.shapes.medium),
                    contentAlignment = Alignment.Center,
                ){
                    Text(
                        text = value.getOrNull(i)?.toString() ?: "",
                        fontFamily = sfProDisplayFontFamily,
                        fontSize = 20.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.W400,
                    )
                }
            }
        }
    }

}