package com.hmc.rutasnavas.features.routes.data.remote

import com.google.gson.annotations.SerializedName

data class RouteApiModel(
    @SerializedName("features") val features: List<FeatureApiModel>
)

data class FeatureApiModel(
    @SerializedName("geometry") val geometry: List<GeometryApiModel>
)

data class GeometryApiModel(
    @SerializedName("coordinates") val coordinates: List<List<Double>>
)
