package com.example.smartlab.common.ui.button

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.smartlab.R
import com.example.smartlab.common.ui.click.scaleClick
import com.example.smartlab.common.ui.theme.colorDescription

@Composable
internal fun BackButton(
    onClick: ()-> Unit,
) {
    Icon(
        painter = painterResource(R.drawable.ic_back),
        contentDescription = null,
        modifier = Modifier
            .scaleClick(onClick)
            .size(32.dp)
            .background(MaterialTheme.colors.surface, MaterialTheme.shapes.medium)
            .padding(6.dp),
        tint = MaterialTheme.colors.colorDescription,
    )
}