package com.hmc.rutasnavas.features.domain

interface RouteRepository {

    suspend fun getRoutes(): List<Route>
}