package com.example.smartlab.analyzes.view

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.pullRefreshIndicatorTransform
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.smartlab.R
import com.example.smartlab.analyzes.model.AnalyzesEvent
import com.example.smartlab.analyzes.model.AnalyzesState
import com.example.smartlab.common.ui.button.CustomButton
import com.example.smartlab.common.ui.click.scaleClick
import com.example.smartlab.common.ui.edit_text.CustomTextField
import com.example.smartlab.common.ui.theme.colorDescription
import com.example.smartlab.common.ui.theme.sfProDisplayFontFamily
import com.example.smartlab.common.view_model.EventBase
import com.example.smartlab.nav.view.LocalBottomBar
import com.google.accompanist.swiperefresh.SwipeRefresh
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
internal fun ContentView(
    state: AnalyzesState.Success,
    eventBase: EventBase<AnalyzesEvent>,
    goToCart: ()-> Unit,
) {
    val confirmValueChange: (ModalBottomSheetValue) -> Boolean = remember {
        { modalBottomSheetValue ->
            when (modalBottomSheetValue) {
                ModalBottomSheetValue.Hidden -> {
                    eventBase.onEvent(AnalyzesEvent.CloseCatalogEntry)
                    true
                }
                else -> true
            }
        }
    }
    val modalSheetState = rememberModalBottomSheetState(
        ModalBottomSheetValue.Hidden,
        confirmValueChange = confirmValueChange,
        skipHalfExpanded = true
    )
    val localCoroutine = rememberCoroutineScope()
    val lazyListState = rememberLazyListState()
    val pullRefreshState = rememberPullRefreshState(
        state.refreshing, { eventBase.onEvent(AnalyzesEvent.Refresh) }
    )
    val localBottomBarState = LocalBottomBar.current
    BackHandler(state.currentCatalogEntryModel != null) {
        eventBase.onEvent(AnalyzesEvent.CloseCatalogEntry)
    }
    LaunchedEffect(state.currentCatalogEntryModel) {
        if (state.currentCatalogEntryModel != null) {
            localBottomBarState.hide()
            modalSheetState.show()
        } else {
            delay(10L)
            modalSheetState.hide()
            localBottomBarState.show()
        }
    }
    ModalBottomSheetLayout(
        sheetState = modalSheetState,
        sheetContent = {
            CatalogEntryInformationView(
                state.currentCatalogEntryModel,
                added = state.currentCatalogEntryModel?.let {
                    state.setIdCatalogEntry.contains(it.id)
                } == true,
                close = { eventBase.onEvent(AnalyzesEvent.CloseCatalogEntry) },
                buy = {
                    state.currentCatalogEntryModel?.let {
                        eventBase.onEvent(AnalyzesEvent.Add(it))
                    }
                },
                remove = {
                    state.currentCatalogEntryModel?.let {
                        eventBase.onEvent(AnalyzesEvent.Remove(it))
                    }
                }
            )
        },
        sheetBackgroundColor = Color.White,
        sheetShape = RoundedCornerShape(
            topStart = 24.dp,
            topEnd = 24.dp,
        ),
        modifier = Modifier
            .pullRefresh(pullRefreshState),
    ) {
        Column {
            Box(
                contentAlignment = Alignment.TopCenter,
                modifier = Modifier.weight(1f)
            ) {
                BackdropScaffold(
                    scaffoldState = rememberBackdropScaffoldState(BackdropValue.Revealed),
                    peekHeight = 80.dp,
                    backLayerBackgroundColor = MaterialTheme.colors.background,
                    frontLayerBackgroundColor = MaterialTheme.colors.background,
                    frontLayerScrimColor = Color.Unspecified,
                    frontLayerShape = RoundedCornerShape(0),
                    frontLayerElevation = 0.dp,
                    appBar = {
                        CustomTextField(
                            text = "",
                            onTextChange = {},
                            hint = "Искать анализы",
                            icon = R.drawable.ic_search,
                            readOnly = true,
                            modifier = Modifier
                                .padding(20.dp)
                                .scaleClick {

                                }
                        )
                    },
                    backLayerContent = {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(16.dp),
                        ) {
                            Text(
                                text = "Акции и новости",
                                fontWeight = FontWeight.W600,
                                fontFamily = sfProDisplayFontFamily,
                                fontSize = 17.sp,
                                color = MaterialTheme.colors.colorDescription,
                                modifier = Modifier
                                    .padding(horizontal = 20.dp)
                            )
                            LazyRow(
                                horizontalArrangement = Arrangement.spacedBy(16.dp),
                                contentPadding = PaddingValues(horizontal = 20.dp)
                            ) {
                                items(state.promotions) { promotionModel ->
                                    ItemPromotion(promotionModel)
                                }
                            }
                            Text(
                                text = "Каталог анализов",
                                fontWeight = FontWeight.W600,
                                fontFamily = sfProDisplayFontFamily,
                                fontSize = 17.sp,
                                color = MaterialTheme.colors.colorDescription,
                                modifier = Modifier
                                    .padding(horizontal = 20.dp)
                            )
                        }
                    },
                    frontLayerContent = {
                        var currentCatalog by remember {
                            mutableStateOf("")
                        }
                        LaunchedEffect(lazyListState, state.catalog) {
                            snapshotFlow { lazyListState.firstVisibleItemIndex }
                                .collect { position ->
                                    currentCatalog = state.categoryCatalog(position)
                                }
                        }
                        val stateCurrentCatalog = rememberLazyListState()
                        LaunchedEffect(currentCatalog) {
                            val index = state.catalog.keys.indexOf(currentCatalog)
                            if (index >= 0) {
                                stateCurrentCatalog.animateScrollToItem(index)
                            }
                        }
                        Column(
                            verticalArrangement = Arrangement.spacedBy(16.dp),
                            modifier = Modifier.padding(top = 15.dp)
                        ) {
                            LazyRow(
                                state = stateCurrentCatalog,
                                horizontalArrangement = Arrangement.spacedBy(16.dp),
                                contentPadding = PaddingValues(horizontal = 20.dp)
                            ) {
                                for (key in state.catalog.keys) {
                                    item {
                                        CardPromoView(key, currentCatalog == key) {
                                            currentCatalog = key
                                            localCoroutine.launch {
                                                lazyListState.scrollToItem(
                                                    state.positionInCatalogEntry(key)
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                            CatalogView(
                                catalog = state.catalog,
                                lazyListState = lazyListState,
                                eventBase = eventBase,
                                setId = state.setIdCatalogEntry,
                            )
                        }
                    },
                )
                PullRefreshIndicator(
                    refreshing = state.refreshing,
                    state = pullRefreshState,
                )
            }
            AnimatedVisibility(
                visible = state.cartValue > 0,
                enter = fadeIn() + expandVertically(expandFrom = Alignment.Bottom),
                exit = fadeOut() + shrinkVertically(shrinkTowards = Alignment.Bottom),
            ) {
                Box(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Divider(color = Color.Black.copy(0.1f))
                    Row(
                        modifier = Modifier
                            .padding(horizontal = 20.dp, vertical = 24.dp)
                            .scaleClick(goToCart)
                            .background(
                                MaterialTheme.colors.primary,
                                MaterialTheme.shapes.medium
                            )
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_cart),
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier
                                .padding(end = 16.dp)
                                .size(20.dp)
                        )
                        Text(
                            text = "В корзину",
                            fontFamily = sfProDisplayFontFamily,
                            fontWeight = FontWeight.W600,
                            fontSize = 17.sp,
                            color = Color.White,
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Text(
                            text = "${state.cartValue} ₽",
                            fontFamily = sfProDisplayFontFamily,
                            fontWeight = FontWeight.W600,
                            fontSize = 17.sp,
                            color = Color.White,
                        )
                    }
                }
            }
        }
    }
}