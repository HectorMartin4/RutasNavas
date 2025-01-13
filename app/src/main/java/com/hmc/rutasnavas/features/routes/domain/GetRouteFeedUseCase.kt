package com.hmc.rutasnavas.features.routes.domain

import org.koin.core.annotation.Single

@Single
class GetRouteFeedUseCase(private val routeRepository: RouteRepository) {

    operator fun invoke(): List<Route> = routeRepository.getRoutes()
}