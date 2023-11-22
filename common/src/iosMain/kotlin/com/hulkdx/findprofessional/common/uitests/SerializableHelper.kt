package com.hulkdx.findprofessional.common.uitests

import com.hulkdx.findprofessional.common.feature.authentication.signup.model.RegisterRequest
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object SerializableHelper {
    fun decodeAuthRequest(value: String) = Json.decodeFromString<RegisterRequest>(value)
    fun encodeAuthRequest(value: RegisterRequest) = Json.encodeToString(value)
}
