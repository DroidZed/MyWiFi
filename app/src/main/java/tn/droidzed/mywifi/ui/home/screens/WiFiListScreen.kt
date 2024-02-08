package tn.droidzed.mywifi.ui.home.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.currentCompositeKeyHash
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.core.screen.ScreenKey
import cafe.adriel.voyager.core.screen.uniqueScreenKey
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import tn.droidzed.mywifi.data.SampleData
import tn.droidzed.mywifi.ui.home.models.WiFiListModel

class WiFiListScreen : Screen {

    override val key: ScreenKey = uniqueScreenKey

    @Composable
    override fun Content() {
        val screenModel = getScreenModel<WiFiListModel>()
        val state by screenModel.state.observeAsState()

        LaunchedEffect(currentCompositeKeyHash) {
            screenModel.getItems()
        }

        Box(modifier = Modifier.fillMaxSize()) {
            when (val result = state) {
                is WiFiListModel.State.Loading -> CircularProgressIndicator(
                    modifier = Modifier.width(64.dp),
                    color = MaterialTheme.colorScheme.secondary,
                    trackColor = MaterialTheme.colorScheme.surfaceVariant,
                )

                is WiFiListModel.State.Result -> ItemList(result.items)
                else -> Unit
            }
        }
    }

    @Composable
    fun ItemList(wifisList: List<SampleData>) {
        val navigator = LocalNavigator.currentOrThrow
        return LazyColumn(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()

        ) {
            items(
                wifisList,
                key = {
                    it.id
                },
            ) { d ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Button(onClick = { navigator.push(WiFiDetailsScreen(id = d.name)) }) {
                        Text(text = "View Details ${d.id}")
                    }
                }
            }
        }
    }
}