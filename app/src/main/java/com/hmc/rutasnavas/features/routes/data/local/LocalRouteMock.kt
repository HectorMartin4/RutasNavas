package com.hmc.rutasnavas.features.routes.data.local

import com.hmc.rutasnavas.features.routes.domain.Route
import org.koin.core.annotation.Single

@Single
class LocalRouteMock {

    private val routeList: List<Route> = listOf(
        Route(
            id = "1",
            title = "Ruta del Sauco",
            start = "-4.334491,40.606940",
            end = "-4.339421,40.612763"
        ),
        Route(
            id = "2",
            title = "Paseo por el pueblo",
            start = "-4.327933,40.603833",
            end = "-4.337680,40.598874"
        ),
        Route(
            id = "3",
            title = "Atalaya del Valladal",
            start = "-4.328001,40.580065",
            end = "-4.337207,40.574552"
        ),
    )

    fun getRouteList(): List<Route> = routeList

    fun getRouteById(id: String): Route =
        routeList.first { r ->
            r.id == id
        }

}