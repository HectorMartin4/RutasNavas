package com.hmc.rutasnavas.features.routes.data

import com.hmc.rutasnavas.features.routes.data.local.LocalRouteMock
import com.hmc.rutasnavas.features.routes.domain.Route
import com.hmc.rutasnavas.features.routes.domain.RouteRepository

class RouteDataRepository(private val localDataSource: LocalRouteMock) : RouteRepository {

    override fun getRoutes(): List<Route> {
        return localDataSource.getRouteList()
    }
}