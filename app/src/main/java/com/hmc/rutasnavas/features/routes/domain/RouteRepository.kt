package com.hmc.rutasnavas.features.routes.domain

interface RouteRepository {

    fun getRoutes(): List<Route>

    fun getRoute(id: String): Route

    suspend fun createRoute(start: String, end: String): RouteResponse
}