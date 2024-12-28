package com.kuro.mdp.app

import android.app.Application
import com.kuro.mdp.app.di.appPlatformModule
import com.kuro.mdp.app.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

/**
 * Created by: minhthinh.h on 12/11/2024
 *
 * Description:
 */
class AppApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        initKoin {
            androidContext(this@AppApplication)
            androidLogger()
            modules(appPlatformModule())
        }
    }

}