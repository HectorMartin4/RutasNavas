package com.hmc.rutasnavas.app.extensions

import com.google.gson.Gson
import org.koin.core.annotation.Single

interface Serializer {
    fun <T> fromJson(json: String, classObj: Class<T>): T
    fun <T> toJson(obj: T, classObj: Class<T>): String
}

@Single
class GsonJSerializer : Serializer {

    private val gson = Gson()

    override fun <T> fromJson(json: String, classObj: Class<T>): T {
        return gson.fromJson(json, classObj)
    }

    override fun <T> toJson(obj: T, classObj: Class<T>): String {
        return gson.toJson(obj, classObj)
    }
}