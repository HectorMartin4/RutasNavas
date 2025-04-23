package com.hmc.rutasnavas.features.routes.data.remote

import com.google.firebase.database.PropertyName

data class RouteFirebaseModel(
    @get:PropertyName("title") @set:PropertyName("title") var title: String = "",
    @get:PropertyName("start") @set:PropertyName("start") var start: String = "",
    @get:PropertyName("end") @set:PropertyName("end") var end: String = ""
)
