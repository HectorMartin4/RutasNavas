package com.hmc.rutasnavas.features.routes.domain

import com.google.gson.annotations.SerializedName

data class Route(
    val id: String,
    val title: String,
    val start: String,
    val end: String
)


data class RouteResponse(
    @SerializedName("features") val features: List<FeatureResponse>
)

data class FeatureResponse(
    @SerializedName("geometry") val geometry: GeometryResponse
)

data class GeometryResponse(
    @SerializedName("coordinates") val coordinates: List<List<Double>>
)