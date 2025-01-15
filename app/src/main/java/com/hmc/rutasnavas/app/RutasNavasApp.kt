package com.hmc.rutasnavas.app

import android.app.Application
import com.hmc.rutasnavas.app.di.AppModule
import com.hmc.rutasnavas.app.di.RemoteModule
import com.hmc.rutasnavas.features.routes.di.RouteModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.ksp.generated.module

class RutasNavasApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@RutasNavasApp)
            modules(
                AppModule().module,
                RemoteModule().module,
                RouteModule().module
            )
        }
    }

}