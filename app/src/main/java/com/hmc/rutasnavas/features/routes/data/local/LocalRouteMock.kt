package com.hmc.rutasnavas.features.routes.data.local

import com.hmc.rutasnavas.features.routes.domain.Route

class LocalRouteMock {

    private val routeList: List<Route> = listOf(

    )

    fun getRouteList(): List<Route> = routeList

    fun getRouteById(id: String): Route =
        routeList.first { r ->
            r.id == id
        }
}