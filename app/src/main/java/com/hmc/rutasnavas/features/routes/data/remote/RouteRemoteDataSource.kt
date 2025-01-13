package com.hmc.rutasnavas.features.routes.data.remote

import com.hmc.rutasnavas.app.data.remote.apiCall
import org.koin.core.annotation.Single

@Single
class RouteRemoteDataSource(private val apiService: ApiService) {

    suspend fun createRoute(key: String, start: String, end: String) {
        apiCall {
            apiService.getRoute(key, start, end)
        }.toDomain()
    }
}