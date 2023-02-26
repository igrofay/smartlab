package com.example.smartlab.common.ui.click

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.input.pointer.pointerInput

private enum class ButtonState {
    Pressed, Idl
}

@Composable
internal fun Modifier.alphaClick(
    onClick: () -> Unit
) = composed {
    var state by remember {
        mutableStateOf(ButtonState.Idl)
    }
    val animation by animateFloatAsState(
        targetValue = if (state == ButtonState.Pressed) 0.75f else 1f
    )
    this
        .alpha(animation)
        .clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = null,
            onClick = onClick,
        )
        .pointerInput(state) {
            awaitPointerEventScope {
                state = when(state){
                    ButtonState.Pressed -> {
                        waitForUpOrCancellation()
                        ButtonState.Idl
                    }
                    ButtonState.Idl -> {
                        awaitFirstDown(false)
                        ButtonState.Pressed
                    }
                }
            }
        }


}