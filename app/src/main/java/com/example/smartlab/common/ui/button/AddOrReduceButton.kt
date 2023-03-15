package com.example.smartlab.common.ui.button

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.smartlab.R
import com.example.smartlab.common.ui.click.scaleClick
import com.example.smartlab.common.ui.theme.colorDescription
import com.example.smartlab.common.ui.theme.colorItemBottomBar
import com.example.smartlab.common.ui.theme.lightGray

@Composable
internal fun AddOrReduceButton(
    add: ()-> Unit,
    reduce: ()-> Unit,
    enabledAdd: Boolean,
    enabledReduce: Boolean,
    shape: Shape = RoundedCornerShape(8.dp),
) {
    Row(
        modifier =
        Modifier
            .clip(shape)
            .background(MaterialTheme.colors.surface, shape)
            .padding(6.dp),
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_remove),
            contentDescription = null,
            modifier = Modifier
                .size(20.dp)
                .scaleClick(
                    if (enabledReduce) reduce else null
                ),
            tint = if (enabledReduce) MaterialTheme.colors.colorDescription else colorItemBottomBar
        )
        Spacer(modifier = Modifier
            .height(16.dp)
            .width(1.dp)
            .background(lightGray)
        )
        Icon(
            painter = painterResource(R.drawable.ic_add),
            contentDescription = null,
            modifier = Modifier
                .size(20.dp)
                .scaleClick(
                    if (enabledAdd) add else null
                ),
            tint = if (enabledAdd) MaterialTheme.colors.colorDescription else colorItemBottomBar
        )
    }
}