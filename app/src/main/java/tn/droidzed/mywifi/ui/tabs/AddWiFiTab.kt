package tn.droidzed.mywifi.ui.tabs

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions

object AddWiFiTab : Tab {
    override val options: TabOptions
        @Composable get() {
            val icon = rememberVectorPainter(Icons.Default.AddCircle)
            return remember {
                TabOptions(
                    index = 1u, title = "Add WiFi", icon = icon
                )
            }
        }

    @Composable
    override fun Content() {
        Text(text = "Add Wifi !")
    }


}