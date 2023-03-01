package com.example.smartlab.code_entry.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smartlab.code_entry.model.CodeEntryEvent
import com.example.smartlab.common.ui.theme.sfProDisplayFontFamily
import com.example.smartlab.common.view_model.EventBase
import com.example.smartlab.R
import com.example.smartlab.common.ui.click.alphaClick

@Composable
internal fun KeyboardView(
    eventBase: EventBase<CodeEntryEvent>
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        for (colum in 0..2) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                for (row in 1..3) {
                    Box(
                        modifier = Modifier
                            .size(80.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colors.surface)
                            .clickable(
                                interactionSource = remember {
                                    MutableInteractionSource()
                                },
                                indication = rememberRipple(
                                    bounded = true,
                                    color = MaterialTheme.colors.primary
                                )
                            ) {
                                eventBase.onEvent(
                                    CodeEntryEvent.EnteringCodeCharacter('0' + colum * 3 + row)
                                )
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "${colum * 3 + row}",
                            fontWeight = FontWeight.W600,
                            fontSize = 24.sp,
                            fontFamily = sfProDisplayFontFamily,
                            color = Color.Black,
                        )
                    }
                }
            }
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Spacer(
                modifier = Modifier
                    .size(80.dp),
            )
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colors.surface)
                    .clickable(
                        interactionSource = remember {
                            MutableInteractionSource()
                        },
                        indication = rememberRipple(
                            bounded = true,
                            color = MaterialTheme.colors.primary
                        )
                    ) {
                        eventBase.onEvent(
                            CodeEntryEvent.EnteringCodeCharacter('0')
                        )
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "0",
                    fontWeight = FontWeight.W600,
                    fontSize = 24.sp,
                    fontFamily = sfProDisplayFontFamily,
                    color = Color.Black,
                )
            }
            Box(
                modifier = Modifier.size(80.dp),
                contentAlignment = Alignment.Center,
            ){
                Icon(
                    painter = painterResource(R.drawable.ic_delete) ,
                    contentDescription = null,
                    modifier = Modifier
                        .alphaClick {
                            eventBase.onEvent(
                                CodeEntryEvent.DeleteCodeCharacter
                            )
                        }
                )
            }

        }
    }
}
