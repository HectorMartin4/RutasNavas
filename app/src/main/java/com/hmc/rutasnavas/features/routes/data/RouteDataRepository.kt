package com.hmc.rutasnavas.features.routes.data

import com.hmc.rutasnavas.features.routes.data.local.LocalRouteMock
import com.hmc.rutasnavas.features.routes.data.remote.RouteRemoteDataSource
import com.hmc.rutasnavas.features.routes.domain.Route
import com.hmc.rutasnavas.features.routes.domain.RouteRepository
import com.hmc.rutasnavas.features.routes.domain.RouteResponse

class RouteDataRepository(
    private val localDataSource: LocalRouteMock,
    private val remoteDataSource: RouteRemoteDataSource
) : RouteRepository {

    override fun getRoutes(): List<Route> {
        return localDataSource.getRouteList()
    }

    override fun getRoute(id: String): Route {
        return localDataSource.getRouteById(id)
    }

    override suspend fun createRoute(start: String, end: String): RouteResponse {
        return remoteDataSource.createRoute(start, end)
    }
}