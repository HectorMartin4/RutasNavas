package com.hmc.rutasnavas.features.routes.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hmc.rutasnavas.features.routes.domain.DeleteRouteUseCase
import com.hmc.rutasnavas.features.routes.domain.GetRoutesFeedUseCase
import com.hmc.rutasnavas.features.routes.domain.Route
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class RouteViewModel(
    private val getRoutesFeedUseCase: GetRoutesFeedUseCase,
    private val deleteRouteUseCase: DeleteRouteUseCase
) : ViewModel() {

    private val _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState> = _uiState

    fun loadRoutes() {
        _uiState.value = UiState(isLoading = true)
        viewModelScope.launch(Dispatchers.IO) {
            val feed = getRoutesFeedUseCase.invoke()
            _uiState.postValue(UiState(routes = feed))
        }
    }

    fun deleteRoute(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteRouteUseCase.invoke(id)
            val feed = getRoutesFeedUseCase.invoke()
            _uiState.postValue(UiState(routes = feed))
        }
    }

    data class UiState(
        val isLoading: Boolean = false,
        val routes: List<Route> = emptyList()
    )
}