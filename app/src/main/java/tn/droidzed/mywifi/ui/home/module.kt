package tn.droidzed.mywifi.ui.home

import org.koin.dsl.module
import tn.droidzed.mywifi.ui.home.models.WiFiListModel

val homeModule = module {
    factory { WiFiListModel() }
}
