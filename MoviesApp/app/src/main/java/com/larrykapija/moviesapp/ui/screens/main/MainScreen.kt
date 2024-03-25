package com.larrykapija.moviesapp.ui.screens.main

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Text
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.mutableIntStateOf
import com.larrykapija.moviesapp.ui.screens.home.HomePage
import com.larrykapija.moviesapp.ui.screens.search.SearchPage
import com.larrykapija.moviesapp.ui.screens.watchlist.WatchListPage

@Composable
fun MainScreen() {
    val tabItems = listOf(
        "Home",
        "Search",
        "Watchlist"
    )

    var currentTabIndex by remember { mutableIntStateOf(0) }

    Scaffold(
        bottomBar = {
            NavigationBar {
                tabItems.forEachIndexed { index, label ->
                    NavigationBarItem(
                        icon = {},
                        label = { Text(label) },
                        selected = currentTabIndex == index,
                        onClick = { currentTabIndex = index }
                    )
                }
            }
        }
    ) {
        MainScreenContent(currentTabIndex = currentTabIndex, innerPadding = it)
    }
}

@Composable
fun MainScreenContent(currentTabIndex: Int, innerPadding: PaddingValues) {
    when (currentTabIndex) {
        0 -> HomePage(innerPadding)
        1 -> SearchPage(innerPadding)
        2 -> WatchListPage(innerPadding)
    }
}