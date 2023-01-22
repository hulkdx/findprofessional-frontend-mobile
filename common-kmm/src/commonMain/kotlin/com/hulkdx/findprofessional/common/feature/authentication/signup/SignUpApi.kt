package com.hulkdx.findprofessional.common.feature.authentication.signup

import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*


class SignUpApi(
    private val client: HttpClient,
) {
    // TODO:
    suspend fun greeting(): String {
        val response = client.get("https://ktor.io/docs/")
        return response.bodyAsText()
    }
}
