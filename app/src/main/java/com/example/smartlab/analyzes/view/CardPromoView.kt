package com.example.smartlab.analyzes.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.domain.model.catalog.PromotionModel
import com.example.smartlab.common.ui.theme.sfProDisplayFontFamily
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage
import kotlin.math.pow
import kotlin.random.Random

@Composable
internal fun ItemPromotion(
    promotionModel: PromotionModel,
) {
    val firstColor = remember {
        Color(
            Random.nextInt(0, 120),
            Random.nextInt(0, 120),
            Random.nextInt(180, 256)
        )
    }
    val secondColor = remember {
        firstColor.copy(
            red = firstColor.red.pow(0.4f),
            green = firstColor.green.pow(0.4f),
            blue = firstColor.blue.pow(0.4f),
        )
    }
    val brush = Brush.horizontalGradient(
        listOf(firstColor, secondColor)
    )
    Box(
        modifier = Modifier
            .clip(MaterialTheme.shapes.medium)
            .background(brush, MaterialTheme.shapes.medium)
            .width(280.dp)
            .height(152.dp)
    ) {
        GlideImage(
            imageModel = { promotionModel.image },
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(0.48f)
                .align(Alignment.CenterEnd),
            imageOptions = ImageOptions(
                alignment = Alignment.Center,
                contentScale = ContentScale.FillHeight
            )
        )
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(0.7f)
                .align(Alignment.CenterStart)
                .padding(top = 16.dp, start = 16.dp, bottom = 12.dp)
        ) {
            Text(
                text = promotionModel.name,
                fontFamily = sfProDisplayFontFamily,
                fontSize = 20.sp,
                fontWeight = FontWeight.W800,
                color = Color.White,
                maxLines = 2
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = promotionModel.name,
                fontFamily = sfProDisplayFontFamily,
                fontSize = 14.sp,
                fontWeight = FontWeight.W400,
                color = Color.White,
                maxLines = 2
            )
            Text(
                text = "${promotionModel.price} ₽",
                fontFamily = sfProDisplayFontFamily,
                fontSize = 20.sp,
                fontWeight = FontWeight.W800,
                color = Color.White,
                maxLines = 2
            )
        }

    }
}