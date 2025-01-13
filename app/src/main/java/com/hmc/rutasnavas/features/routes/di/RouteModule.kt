package com.hmc.rutasnavas.features.routes.di

import com.hmc.rutasnavas.features.routes.data.remote.ApiService
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single
import retrofit2.Retrofit

@Module
@ComponentScan
class RouteModule {

    @Single
    fun provideRouteService(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)
}