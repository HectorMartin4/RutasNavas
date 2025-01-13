package com.hmc.rutasnavas.features.routes.domain

data class Route(
    val id: Int,
    val title: String,
    val features: List<Feature>
)

data class Feature(
    val geometry: List<Geometry>
)

data class Geometry(
    val coordinates: List<List<Double>>
)