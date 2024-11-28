package com.hmc.rutasnavas.features.routes.domain

data class Route(
    val features: List<Feature>
)

data class Feature(
    val geometry: List<Geometry>
)

data class Geometry(
    val xd: List<List<Double>>
)