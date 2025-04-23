package com.hmc.rutasnavas.features.remote

import com.hmc.rutasnavas.features.routes.domain.RouteResponse
import org.koin.core.annotation.Single

@Single
class RouteRemoteDataSource(private val apiService: ApiService) {

    suspend fun createRoute(apiKey: String, start: String, end: String): RouteResponse {
        val call = apiService.getRoute(
            apiKey,
            start,
            end
        )
        if (call.isSuccessful) {
            return call.body()!!

        } else {
            throw Exception("Error al crear la ruta, llamada a la API falllida")
        }
    }
}