package com.hulkdx.findprofessional.common.feature.authentication.signup

import com.hulkdx.findprofessional.common.feature.authentication.signup.model.RegisterRequest
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*


class SignUpApi(
    private val client: HttpClient,
) {
    // TODO: change baseUrl for debug, prod
    private val baseUrl = "http://10.0.2.2:8080"

    suspend fun register(): String {
        val response = client.post("$baseUrl/auth/register") {
            contentType(ContentType.Application.Json)
            setBody(RegisterRequest("user3@gmail.comu", "12332423adfs"))
        }
        return response.bodyAsText()
    }
}

