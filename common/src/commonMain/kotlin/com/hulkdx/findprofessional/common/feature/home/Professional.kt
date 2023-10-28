package com.hulkdx.findprofessional.common.feature.home

import com.hulkdx.findprofessional.common.utils.CommonParcelable
import com.hulkdx.findprofessional.common.utils.CommonParcelize
import kotlinx.serialization.Serializable

typealias Availability = List<List<String>>

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
    val description: String? = null,
    // TODO: add to backend:
    val availabilities: Availability,
    val totalReviews: String = "0",
    val reviews: List<Review>,
) : CommonParcelable {
    val fullName: String
        get() = "$firstName $lastName"
    val price: String
        get() = "$priceCurrency $priceNumber"
}

@CommonParcelize
@Serializable
data class Review(
    val profileImageUrl: String,
    val firstName: String,
    val lastName: String,
    val star: Int,
    val text: String,
    val date: String,
): CommonParcelable {
    val fullName: String
        get() = "$firstName $lastName"
}
