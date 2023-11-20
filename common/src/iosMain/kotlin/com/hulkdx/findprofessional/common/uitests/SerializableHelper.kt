package com.hulkdx.findprofessional.common.uitests

import com.hulkdx.findprofessional.common.feature.authentication.signup.model.LoginRequest
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object SerializableHelper {
    fun decodeAuthRequest(value: String) = Json.decodeFromString<LoginRequest>(value)
    fun encodeAuthRequest(value: LoginRequest) = Json.encodeToString(value)
}
