package com.unknowncompany.pandatechtechtest

import android.app.Application
import com.unknowncompany.pandatechtechtest.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class PTTApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@PTTApplication)
            modules(listOf(viewModelModule))
        }
    }

}