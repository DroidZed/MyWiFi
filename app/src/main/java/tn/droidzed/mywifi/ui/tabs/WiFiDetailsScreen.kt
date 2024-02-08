package tn.droidzed.mywifi.ui.tabs

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen

class WiFiDetailsScreen(private val id: String) : Screen {

    @Composable
    override fun Content() {
        Box(modifier = Modifier.fillMaxSize()) {

            Text(text = "wifi details, id: $id")
        }
    }
}