package com.hulkdx.findprofessional.common.feature.authentication.signup.model

import com.hulkdx.findprofessional.common.utils.CommonParcelable
import com.hulkdx.findprofessional.common.utils.CommonParcelize
import kotlinx.serialization.Serializable

@Serializable
@CommonParcelize
data class RegisterRequest(
    val email: String,
    val password: String,
    val firstName: String,
    val lastName: String,
    val profileImage: String?,
) : CommonParcelable
