package io.github.xiue233.normalizer.ui.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import io.github.xiue233.normalizer.ui.components.MessageTopBar
import io.github.xiue233.normalizer.ui.theme.getColorScheme
import kotlinx.coroutines.launch

private sealed class BottomNavigationItem(
    val name: String,
    val icon: ImageVector,
) {
    object SYNC : BottomNavigationItem(
        "同步",
        Icons.Filled.Send,
    )

    object HOME : BottomNavigationItem(
        "主页",
        Icons.Filled.Home,
    )

    object SETTINGS :
        BottomNavigationItem(
            "设置",
            Icons.Filled.Settings,
        )
}

private val BOTTOM_NAVIGATION_ITEMS =
    listOf(BottomNavigationItem.SYNC, BottomNavigationItem.HOME, BottomNavigationItem.SETTINGS)

@Preview
@Composable
fun MainScreenPreview() {
    MainScreen()
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreen() {
    rememberSystemUiController().run {
        setStatusBarColor(getColorScheme().primary)
    }

    val scaffoldState = rememberScaffoldState()
    val pagerState = rememberPagerState()
    val coroutine = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        pagerState.scrollToPage(1)
    }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            MessageTopBar("MatePad正在发送消息...")
        },
        bottomBar = {
            Column {
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                )
                BottomNavigation(
                    backgroundColor = Color.White,
                    contentColor = getColorScheme().primary
                ) {
                    for (i in (BOTTOM_NAVIGATION_ITEMS.indices)) {
                        val isSelected = remember(pagerState.currentPage) {
                            i == pagerState.currentPage
                        }
                        BottomNavigationItem(
                            selected = isSelected,
                            onClick = {
                                coroutine.launch {
                                    pagerState.scrollToPage(i)
                                }
                            },
                            icon = {
                                Icon(
                                    imageVector = BOTTOM_NAVIGATION_ITEMS[i].icon,
                                    contentDescription = BOTTOM_NAVIGATION_ITEMS[i].name,
                                )
                            },
                            label = {
                                Text(
                                    text = BOTTOM_NAVIGATION_ITEMS[i].name,
                                    style = TextStyle(color = getColorScheme().primary),
                                )
                            }
                        )
                    }
                }
            }
        },
        content = {
            HorizontalPager(
                pageCount = BOTTOM_NAVIGATION_ITEMS.size, state = pagerState,
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize(),
            ) { pageIndex ->
                when (pageIndex) {
                    0 -> {
                        Text(text = "0")
                    }

                    1 -> {
                        Text(text = "1")
                    }

                    2 -> {
                        Text(text = "2")
                    }
                }
            }
        }
    )
}