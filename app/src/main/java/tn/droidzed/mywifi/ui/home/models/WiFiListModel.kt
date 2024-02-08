package tn.droidzed.mywifi.ui.home.models

import cafe.adriel.voyager.core.model.screenModelScope
import cafe.adriel.voyager.livedata.LiveScreenModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import tn.droidzed.mywifi.data.SampleData
import tn.droidzed.mywifi.data.sampleData


class WiFiListModel : LiveScreenModel<WiFiListModel.State>(State.Init) {

    val wifisList = mutableListOf<SampleData>()

    sealed class State {
        data object Init : State()
        data object Loading : State()
        data class Result(val items: List<SampleData>) : State()
    }

    override fun onDispose() {
        super.onDispose()
        wifisList.clear()
    }

    fun getItems() {
        screenModelScope.launch {
            delay(1_000)
            mutableState.postValue(State.Result(sampleData))
        }
    }


}