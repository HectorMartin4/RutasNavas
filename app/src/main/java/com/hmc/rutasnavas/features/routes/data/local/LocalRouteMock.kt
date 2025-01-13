package com.hmc.rutasnavas.features.routes.data.local

import com.hmc.rutasnavas.features.routes.domain.Route

class LocalRouteMock {

    private val routeList: List<Route> = listOf(
        Route()
    )

    fun getRouteList(): List<Route> = routeList

    fun getRouteById(id: Int): Route =
        routeList.first { r ->
            r.id == id
        }
}