package com.hmc.rutasnavas.features.routes.data.local

import android.content.Context
import com.hmc.rutasnavas.app.extensions.Serializer
import com.hmc.rutasnavas.features.routes.domain.Route
import org.koin.core.annotation.Single

@Single
class RouteXmlLocalDataSource(private val context: Context, private val serializer: Serializer) {

    private val sharedPref = context.getSharedPreferences("Events", Context.MODE_PRIVATE)

    fun saveRoute(route: Route) {
        sharedPref.edit().apply {
            putString(route.id, serializer.toJson(route, Route::class.java))
        }.apply()
    }

    fun saveAllRoutes(routes: List<Route>) {
        routes.map {
            saveRoute(it)
        }
    }

    fun getAllRoutes(): List<Route> {
        return sharedPref.all.map {
            serializer.fromJson(it.value as String, Route::class.java)
        }
    }

    fun findRouteById(routeId: String): Route {
        return serializer
            .fromJson(sharedPref.getString(routeId, null)!!, Route::class.java)
    }

    fun deleteRouteById(routeId: String) {
        sharedPref.edit().apply {
            remove(routeId)
        }.apply()
    }

}