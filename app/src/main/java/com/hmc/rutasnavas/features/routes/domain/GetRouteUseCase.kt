package com.hmc.rutasnavas.features.routes.domain

import org.koin.core.annotation.Single

@Single
class GetRouteUseCase(private val routeRepository: RouteRepository) {

    operator fun invoke(id: String): Route = routeRepository.getRoute(id)
}