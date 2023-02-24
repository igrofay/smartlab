package com.example.smartlab.splash.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.smartlab.R

@Composable
fun SplashLogoView() {
    Image(
        painter = painterResource(R.drawable.im_screensaver),
        contentDescription = null,
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.FillHeight
    )
}