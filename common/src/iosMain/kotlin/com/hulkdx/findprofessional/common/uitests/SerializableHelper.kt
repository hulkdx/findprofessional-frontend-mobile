package com.hulkdx.findprofessional.common.uitests

import com.hulkdx.findprofessional.common.feature.authentication.signup.model.AuthRequest
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object SerializableHelper {
    fun decodeAuthRequest(value: String) = Json.decodeFromString<AuthRequest>(value)
    fun encodeAuthRequest(value: AuthRequest) = Json.encodeToString(value)
}
