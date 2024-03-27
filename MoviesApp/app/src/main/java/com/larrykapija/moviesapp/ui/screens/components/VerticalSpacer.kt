package com.larrykapija.moviesapp.ui.screens.components

import android.health.connect.datatypes.units.Percentage
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp

@Composable
fun VerticalSpacer(
    percentage: Int
) {
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp

    val space = screenHeight / (100 / percentage)

    Spacer(modifier = Modifier.height(space.dp))
}