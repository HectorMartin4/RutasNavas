package com.hmc.rutasnavas.features.routes.data

import com.hmc.rutasnavas.features.remote.RouteRemoteDataSource
import com.hmc.rutasnavas.features.routes.data.local.RouteXmlLocalDataSource
import com.hmc.rutasnavas.features.routes.data.remote.RouteFirebaseDataSource
import com.hmc.rutasnavas.features.routes.domain.Route
import com.hmc.rutasnavas.features.routes.domain.RouteRepository
import com.hmc.rutasnavas.features.routes.domain.RouteResponse
import org.koin.core.annotation.Single

@Single
class RouteDataRepository(
    private val firebaseDataSource: RouteFirebaseDataSource,
    private val localDataSource: RouteXmlLocalDataSource,
    private val remoteDataSource: RouteRemoteDataSource
) : RouteRepository {

    override suspend fun getRoutes(): List<Route> {
        val localRoutes = localDataSource.getAllRoutes()

        return localRoutes.ifEmpty {
            val mock = firebaseDataSource.getRoutes()
            localDataSource.saveAllRoutes(mock)
            mock
        }
    }

    override fun getRoute(id: String): Route {
        return localDataSource.findRouteById(id)
    }

    override suspend fun createRoute(apiKey: String, start: String, end: String): RouteResponse {
        return remoteDataSource.createRoute(apiKey, start, end)
    }

    override fun saveRoute(route: Route) {
        localDataSource.saveRoute(route)
    }

    override fun deleteRoute(id: String) {
        localDataSource.deleteRouteById(id)
    }
}