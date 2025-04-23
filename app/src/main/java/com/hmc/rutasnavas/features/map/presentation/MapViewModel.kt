package com.hmc.rutasnavas.features.map.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hmc.rutasnavas.features.routes.domain.CreateRouteUseCase
import com.hmc.rutasnavas.features.routes.domain.Route
import com.hmc.rutasnavas.features.routes.domain.RouteResponse
import com.hmc.rutasnavas.features.routes.domain.SaveRouteUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class MapViewModel(
    private val createRouteUseCase: CreateRouteUseCase,
    private val saveRouteUseCase: SaveRouteUseCase
) : ViewModel() {

    suspend fun createRoute(apiKey: String, start: String, end: String): RouteResponse {
        return createRouteUseCase.invoke(apiKey, start, end)
    }

    fun saveRoute(route: Route) {
        viewModelScope.launch(Dispatchers.IO) {
            saveRouteUseCase.invoke(route)
        }
    }


}