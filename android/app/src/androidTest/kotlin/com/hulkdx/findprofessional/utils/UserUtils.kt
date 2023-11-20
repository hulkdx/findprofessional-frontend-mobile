package com.hulkdx.findprofessional.utils

import com.hulkdx.findprofessional.common.feature.authentication.model.User


fun newUser(
    email: String = "lucy.mueller@example.com",
    firstName: String = "Whitney Johns",
    lastName: String = "Clifton Haynes",
    profileImage: String? = null
) = User(
    email = email,
    firstName = firstName,
    lastName = lastName,
    profileImage = profileImage
)
