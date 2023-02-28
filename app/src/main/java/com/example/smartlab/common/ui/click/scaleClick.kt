package com.example.smartlab.common.ui.click

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.input.pointer.pointerInput

private enum class ScaleButtonState {
    Pressed, Idl
}

internal fun Modifier.scaleClick(
    onClick: (() -> Unit)?
) = composed {
    var state by remember {
        mutableStateOf(ScaleButtonState.Idl)
    }
    val animation by animateFloatAsState(
        targetValue = if (state == ScaleButtonState.Pressed && onClick != null)
            0.75f
        else 1f
    )
    this
        .scale(animation)
        .clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = null,
            onClick = onClick ?: {},
        )
        .pointerInput(state) {
            awaitPointerEventScope {
                state = when(state){
                    ScaleButtonState.Pressed -> {
                        waitForUpOrCancellation()
                        ScaleButtonState.Idl
                    }
                    ScaleButtonState.Idl -> {
                        awaitFirstDown(false)
                        ScaleButtonState.Pressed
                    }
                }
            }
        }


}