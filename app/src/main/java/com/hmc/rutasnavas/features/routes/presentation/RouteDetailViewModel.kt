package com.hmc.rutasnavas.features.routes.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.hmc.rutasnavas.features.routes.domain.CreateRouteUseCase
import com.hmc.rutasnavas.features.routes.domain.GetRouteUseCase
import com.hmc.rutasnavas.features.routes.domain.Route
import com.hmc.rutasnavas.features.routes.domain.RouteResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class RouteDetailViewModel(
    private val getRouteUseCase: GetRouteUseCase,
    private val createRouteUseCase: CreateRouteUseCase
) : ViewModel() {

    private val _uiState = MutableLiveData<RouteUiState>()
    val routeUiState: LiveData<RouteUiState> = _uiState

    fun loadRoute(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val route = getRouteUseCase.invoke(id)
            _uiState.postValue(RouteUiState(route = route))
        }
    }

    suspend fun createRoute(start: String, end: String): RouteResponse {
        return createRouteUseCase.invoke(start, end)
    }

    fun routeMarker(map: GoogleMap) {

        val locationString = routeUiState.value?.route?.start

        val parts = locationString!!.split(",")
        val latLng = LatLng(parts[1].toDouble(), parts[0].toDouble())

        map.addMarker(
            MarkerOptions().position(latLng).title("Inicio Ruta")
        )?.showInfoWindow()
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 14f))
    }
    data class RouteUiState(
        val route: Route? = null
    )
}