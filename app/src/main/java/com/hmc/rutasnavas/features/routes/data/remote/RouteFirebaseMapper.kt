package com.hmc.rutasnavas.features.routes.data.remote

import com.hmc.rutasnavas.features.routes.domain.Route

fun RouteFirebaseModel.toDomain(): Route =
    Route(
        title = this.title,
        start = this.start,
        end = this.end
    )