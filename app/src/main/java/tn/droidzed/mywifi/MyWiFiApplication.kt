package tn.droidzed.mywifi

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import tn.droidzed.mywifi.ui.home.homeModule

class MyWiFiApplication : Application() {

    private val modules = listOf(homeModule)

    override fun onCreate() {
        super.onCreate()

        startKoin {
            // Log Koin into Android logger
            androidLogger()
            // Reference Android context
            androidContext(this@MyWiFiApplication)
            // Load modules
            modules(modules)
        }
    }
}