package tn.droidzed.mywifi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabDisposable
import cafe.adriel.voyager.navigator.tab.TabNavigator
import tn.droidzed.mywifi.ui.tabs.AddWiFiTab
import tn.droidzed.mywifi.ui.tabs.HomeTab
import tn.droidzed.mywifi.ui.tabs.SettingsTab
import tn.droidzed.mywifi.ui.theme.MyWiFiTheme

@ExperimentalMaterial3Api
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()

        setContent {
            MyWiFiTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TabNavigator(
                        HomeTab,
                        tabDisposable = {
                            TabDisposable(
                                navigator = it,
                                tabs = listOf(HomeTab, AddWiFiTab, SettingsTab)
                            )
                        }
                    ) { tabNavigator ->
                        Scaffold(
                            topBar = {
                                TopAppBar(
                                    title = { Text(text = tabNavigator.current.options.title) }
                                )
                            },
                            content = { paddingValues ->
                                Box(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(paddingValues)
                                ) {
                                    CurrentTab()
                                }
                            },
                            bottomBar = {
                                BottomNavigation {
                                    TabNavigationItem(HomeTab)
                                    TabNavigationItem(AddWiFiTab)
                                    TabNavigationItem(SettingsTab)
                                }
                            }
                        )
                    }
                }
            }
        }
    }

    @Composable
    private fun RowScope.TabNavigationItem(tab: Tab) {
        val tabNavigator = LocalTabNavigator.current

        BottomNavigationItem(
            selected = tabNavigator.current.key == tab.key,
            onClick = { tabNavigator.current = tab },
            icon = { Icon(painter = tab.options.icon!!, contentDescription = tab.options.title) }
        )
    }
}
