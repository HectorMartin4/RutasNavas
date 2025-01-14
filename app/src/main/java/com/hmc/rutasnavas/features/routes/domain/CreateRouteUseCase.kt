package com.hmc.rutasnavas.features.routes.domain

import org.koin.core.annotation.Single

@Single
class CreateRouteUseCase(private val routeRepository: RouteRepository) {

    suspend operator fun invoke(start: String, end: String): RouteResponse =
        routeRepository.createRoute(start, end)
}