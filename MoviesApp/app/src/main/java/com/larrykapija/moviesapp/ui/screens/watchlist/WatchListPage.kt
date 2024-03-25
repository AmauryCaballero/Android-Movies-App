package com.larrykapija.moviesapp.ui.screens.watchlist

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun WatchListPage(innerPadding: PaddingValues) {
    Box(modifier = Modifier.padding(innerPadding), contentAlignment = Alignment.Center) {
        Text(text = "Watch List Page")
    }
}