package com.hmc.rutasnavas.features.routes.domain

interface RouteRepository {

    fun getRoutes(): List<Route>
}