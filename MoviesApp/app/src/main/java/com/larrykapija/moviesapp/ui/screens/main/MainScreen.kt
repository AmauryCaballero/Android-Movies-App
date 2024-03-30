package com.larrykapija.moviesapp.ui.screens.main

import android.content.res.Configuration
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.larrykapija.moviesapp.ui.screens.home.HomePage
import com.larrykapija.moviesapp.ui.screens.main.components.CurvedBottomNavigationBar
import com.larrykapija.moviesapp.ui.screens.search.SearchPage
import com.larrykapija.moviesapp.ui.screens.splash.SplashScreen
import com.larrykapija.moviesapp.ui.screens.watchlist.WatchListPage
import com.larrykapija.moviesapp.ui.theme.MoviesAppTheme
import com.larrykapija.moviesapp.viewmodel.HomePageViewModel

@Composable
fun MainScreen(
    navController: NavController
) {
    var currentTabIndex by remember { mutableIntStateOf(0) }

    Scaffold(
        bottomBar = {
            CurvedBottomNavigationBar(
                selectedIndex = currentTabIndex,
                onItemSelected = { currentTabIndex = it }
            )
        }
    ) {
        when (currentTabIndex) {
            0 -> HomePage(navController,it)
            1 -> SearchPage(navController,it)
            2 -> WatchListPage(navController,it)
        }
    }
}


//@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
//@Composable
//fun MainScreenPreviewLight() {
//    MoviesAppTheme {
//        MainScreen()
//    }
//}
//
//@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
//@Composable
//fun MainScreenPreviewDark() {
//    MoviesAppTheme {
//        MainScreen()
//    }
//}