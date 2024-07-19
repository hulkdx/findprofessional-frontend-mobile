package com.hulkdx.findprofessional.feature.utils

import com.hulkdx.findprofessional.feature.authentication.login.model.User


fun newUser(
    email: String = "lucy.mueller@example.com",
    firstName: String = "Whitney Johns",
    lastName: String = "Clifton Haynes",
    profileImage: String? = null,
) = User(
    email = email,
    firstName = firstName,
    lastName = lastName,
    profileImage = profileImage
)
