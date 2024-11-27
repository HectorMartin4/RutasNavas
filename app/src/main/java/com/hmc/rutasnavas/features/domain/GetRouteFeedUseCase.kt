package com.hmc.rutasnavas.features.domain

import javax.inject.Inject

class GetRouteFeedUseCase @Inject constructor(private val routeRepository: RouteRepository) {

    suspend operator fun invoke(): List<Route> = routeRepository.getRoutes()
}