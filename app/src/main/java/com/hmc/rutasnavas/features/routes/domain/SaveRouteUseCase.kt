package com.hmc.rutasnavas.features.routes.domain

import org.koin.core.annotation.Single

@Single
class SaveRouteUseCase(private val repository: RouteRepository) {

    operator fun invoke(route: Route) {
        repository.saveRoute(route)
    }
}