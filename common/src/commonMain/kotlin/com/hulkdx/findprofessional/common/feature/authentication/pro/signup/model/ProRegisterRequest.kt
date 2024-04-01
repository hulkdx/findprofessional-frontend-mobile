package com.hulkdx.findprofessional.common.feature.authentication.pro.signup.model

import com.hulkdx.findprofessional.common.utils.CommonParcelable
import com.hulkdx.findprofessional.common.utils.CommonParcelize
import kotlinx.serialization.Serializable

@Serializable
@CommonParcelize
data class ProRegisterRequest(
    val email: String = "",
    val password: String = "",
    val firstName: String = "",
    val lastName: String = "",
    val skypeId: String = "",
    val aboutMe: String = "",
    val coachType: String = "",
    val price: String = "",
    val priceCurrency: String = "EUR",
) : CommonParcelable
