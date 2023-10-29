package com.hulkdx.findprofessional.common.feature.home.model

import com.hulkdx.findprofessional.common.utils.CommonParcelable
import com.hulkdx.findprofessional.common.utils.CommonParcelize
import kotlinx.serialization.Serializable

@CommonParcelize
@Serializable
data class ProfessionalReview(
    val total: Int,
    val content: List<Content>,
) : CommonParcelable {

    @CommonParcelize
    @Serializable
    data class Content(
        val userFirstName: String,
        val userLastName: String,
        val userProfileImageUrl: String,
        val star: Int,
        val reviewText: String,
        val reviewDate: String,
    ) : CommonParcelable {

        val userFullName: String
            get() = "$userFirstName $userLastName"
    }
}
