package com.hulkdx.findprofessional.common.feature.home

import com.hulkdx.findprofessional.common.utils.CommonParcelable
import com.hulkdx.findprofessional.common.utils.CommonParcelize
import kotlinx.serialization.Serializable

/**
 *  TODO: what to show if these optional values are not present
 */
@CommonParcelize
@Serializable
data class Professional(
    val id: Int,
    val email: String,
    val firstName: String? = null,
    val lastName: String? = null,
    val coachType: String? = null,
    val priceNumber: Int? = null,
    val priceCurrency: String? = null,
    val profileImageUrl: String? = null,
    val rating: String? = null,
    val description: String? = null
) : CommonParcelable {
    val fullName: String
        get() = "$firstName $lastName"
    val price: String
        get() = "$priceCurrency $priceNumber"
}
