package com.hmc.rutasnavas.features.map.presentation

import androidx.lifecycle.ViewModel
import com.hmc.rutasnavas.features.routes.domain.CreateRouteUseCase
import com.hmc.rutasnavas.features.routes.domain.RouteResponse
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class MapViewModel(private val createRouteUseCase: CreateRouteUseCase) : ViewModel() {

    suspend fun createRoute(start: String, end: String): RouteResponse {
        return createRouteUseCase.invoke(start, end)
    }
}