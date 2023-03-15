package com.example.smartlab.cart.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.smartlab.cart.view_model.CartVM
import com.example.smartlab.common.ui.button.BackButton
import com.example.smartlab.common.ui.theme.sfProDisplayFontFamily
import com.example.smartlab.R
import com.example.smartlab.cart.model.CartEvent
import com.example.smartlab.common.ui.button.CustomButton
import com.example.smartlab.common.ui.click.scaleClick

@Composable
internal fun CartScreen(
    goToBack: ()-> Unit,
    goToOrdering: ()->Unit,
    viewModel: CartVM = hiltViewModel(),
) {
    val state by viewModel.state
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 16.dp)
            .padding(horizontal = 20.dp),
    ) {
        BackButton(goToBack)
        Spacer(modifier = Modifier.height(24.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Корзина",
                fontFamily = sfProDisplayFontFamily,
                fontWeight = FontWeight.W700,
                fontSize = 24.sp,
                color = Color.Black,
            )
            Icon(
                painter = painterResource(R.drawable.ic_trash_bin),
                contentDescription = null,
                modifier = Modifier
                    .size(20.dp)
                    .scaleClick { viewModel.onEvent(CartEvent.ClearCart) }
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(vertical = 16.dp)
        ){
            items(state.catalogCart){ ratio->
                CardRatioView(
                    name = ratio.product.name,
                    number = ratio.amount,
                    price = ratio.product.price,
                    add = {viewModel.onEvent(CartEvent.IncrementNumber(ratio))},
                    decrease = {viewModel.onEvent(CartEvent.DecrementNumber(ratio))},
                    delete = {viewModel.onEvent(CartEvent.Delete(ratio.product))}
                )
            }
        }
        Row(
            modifier = Modifier
                .padding(vertical = 24.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = "Сумма",
                fontFamily = sfProDisplayFontFamily,
                color = Color.Black,
                fontSize = 20.sp,
                fontWeight = FontWeight.W600,
            )
            Text(
                text = "${state.sumCart} ₽",
                fontFamily = sfProDisplayFontFamily,
                color = Color.Black,
                fontSize = 20.sp,
                fontWeight = FontWeight.W600,
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        CustomButton(label = "Перейти к оформлению заказа", onClick = goToOrdering)
        Spacer(modifier = Modifier.height(32.dp))
    }
}