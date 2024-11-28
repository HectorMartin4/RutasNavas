package com.hmc.rutasnavas.features.routes.domain

interface RouteRepository {

    suspend fun getRoutes(): List<Route>
}