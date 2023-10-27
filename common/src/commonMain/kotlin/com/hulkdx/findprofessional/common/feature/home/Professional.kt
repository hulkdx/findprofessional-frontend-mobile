package com.hulkdx.findprofessional.common.feature.home

import com.hulkdx.findprofessional.common.utils.CommonParcelable
import com.hulkdx.findprofessional.common.utils.CommonParcelize
import kotlinx.serialization.Serializable

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
    val availabilities: List<List<String>> = TEST_AVAILABILITIES,
    val totalReviews: String? = "200",
) : CommonParcelable {
    val fullName: String
        get() = "$firstName $lastName"
    val price: String
        get() = "$priceCurrency $priceNumber"
}

// TODO: remove
private val TEST_AVAILABILITIES = listOf(
    listOf("", "Thu\n19", "Fri\n20", "Sat\n21", "Sun\n22", "Mon\n23", "Tue\n24"),
    listOf("00-04", "0", "0", "0", "0", "0", "0"),
    listOf("04-08", "0", "1", "0", "0", "0", "0"),
    listOf("08-12", "0", "0", "2", "0", "0", "0"),
    listOf("12-16", "0", "0", "0", "3", "0", "0"),
    listOf("16-20", "0", "0", "0", "0", "0", "0"),
    listOf("20-24", "0", "0", "0", "0", "4", "0"),
)
