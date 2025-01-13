package com.hmc.rutasnavas.app.data.remote

import retrofit2.Response
import java.io.IOException

suspend fun <T : Any> apiCall(call: suspend () -> Response<T>): T {
    val response: Response<T>
    try {
        response = call.invoke()
    } catch (exception: Throwable) {
        throw IOException("Error de red o conexión: ${exception.message}", exception)
    }

    if (response.isSuccessful && response.body() != null) {
        return response.body()!!
    } else {
        throw Exception("Error en la respuesta del servidor: ${response.code()} - ${response.message()}")
    }
}