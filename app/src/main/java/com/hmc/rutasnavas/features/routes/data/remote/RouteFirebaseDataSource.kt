package com.hmc.rutasnavas.features.routes.data.remote

import com.google.firebase.database.FirebaseDatabase
import com.hmc.rutasnavas.features.routes.domain.Route
import kotlinx.coroutines.tasks.await
import org.koin.core.annotation.Single

@Single
class RouteFirebaseDataSource(private val firebase: FirebaseDatabase) {

    suspend fun getRoutes(): List<Route> {
        return firebase.getReference("Routes").get().await().children.map {
            it.getValue(RouteFirebaseModel::class.java)!!.toDomain()
        }
    }
}