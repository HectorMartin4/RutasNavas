package com.hmc.rutasnavas.features.routes.data.remote

import com.hmc.rutasnavas.features.routes.domain.Feature
import com.hmc.rutasnavas.features.routes.domain.Geometry
import com.hmc.rutasnavas.features.routes.domain.Route

fun RouteApiModel.toDomain(): Route =
    Route(
        title = String(),
        this.features.map {
            it.toDomain()
        }
    )

fun FeatureApiModel.toDomain(): Feature =
    Feature(
        this.geometry.map {
            it.toDomain()
        }
    )

fun GeometryApiModel.toDomain(): Geometry =
    Geometry(
        this.coordinates
    )