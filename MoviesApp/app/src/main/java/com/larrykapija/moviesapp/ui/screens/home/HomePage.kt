package com.larrykapija.moviesapp.ui.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import com.larrykapija.moviesapp.ui.viewmodel.HomePageViewModel

@Composable
fun HomePage(
    innerPadding: PaddingValues,
    viewModel: HomePageViewModel = viewModel(LocalContext.current as ViewModelStoreOwner)
) {
    Box(modifier = Modifier.padding(innerPadding), contentAlignment = Alignment.Center) {
        Text(text = viewModel.greetings)
    }
}