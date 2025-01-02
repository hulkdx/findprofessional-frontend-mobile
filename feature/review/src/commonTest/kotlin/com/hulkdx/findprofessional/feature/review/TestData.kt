package com.hulkdx.findprofessional.feature.review

import com.hulkdx.findprofessional.core.features.pro.model.ProfessionalReview
import com.hulkdx.findprofessional.core.features.user.User
import kotlinx.datetime.Clock

fun createReview() = ProfessionalReview(
    id = 6240,
    user = User(
        email = "stefan.contreras@example.com",
        firstName = "Charley Farrell",
        lastName = "Hubert Sweet",
        profileImage = null
    ),
    rate = 1540,
    contentText = null,
    createdAt = Clock.System.now(),
    updatedAt = Clock.System.now(),
)
