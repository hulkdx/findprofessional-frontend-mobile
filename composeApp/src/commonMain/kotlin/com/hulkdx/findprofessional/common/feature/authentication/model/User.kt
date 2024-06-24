package com.hulkdx.findprofessional.common.feature.authentication.model

import com.hulkdx.findprofessional.common.utils.CommonParcelable
import com.hulkdx.findprofessional.common.utils.CommonParcelize
import kotlinx.serialization.Serializable

@Serializable
@CommonParcelize
data class User(
    val email: String,
    val firstName: String,
    val lastName: String,
    val profileImage: String? = null,
    val skypeId: String? = null,
): CommonParcelable {
    val fullName: String
        get() = "$firstName $lastName"
}
