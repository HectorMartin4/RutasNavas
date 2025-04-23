package com.hmc.rutasnavas.features.routes.domain

interface RouteRepository {

    suspend fun getRoutes(): List<Route>

    fun getRoute(id: String): Route

    suspend fun createRoute(apiKey: String, start: String, end: String): RouteResponse

    fun saveRoute(route: Route)

    fun deleteRoute(id: String)
}