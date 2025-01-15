package com.hmc.rutasnavas.features.routes.domain

import org.koin.core.annotation.Single

@Single
class DeleteRouteUseCase(private val repository: RouteRepository) {

    operator fun invoke(id: String) {
        repository.deleteRoute(id)
    }
}