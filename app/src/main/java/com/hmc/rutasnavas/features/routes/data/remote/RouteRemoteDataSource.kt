package com.hmc.rutasnavas.features.routes.data.remote

import com.hmc.rutasnavas.features.routes.domain.RouteResponse
import org.koin.core.annotation.Single

@Single
class RouteRemoteDataSource(private val apiService: ApiService) {

    suspend fun createRoute(start: String, end: String): RouteResponse {
        val call = apiService.getRoute(
            "5b3ce3597851110001cf6248caca24324fb34ced91430ab931e155b6",
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