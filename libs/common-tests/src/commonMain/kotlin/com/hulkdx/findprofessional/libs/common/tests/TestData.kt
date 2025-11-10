package com.hulkdx.findprofessional.libs.common.tests

import com.hulkdx.findprofessional.core.features.pro.model.Professional
import com.hulkdx.findprofessional.core.features.pro.model.ProfessionalAvailability
import com.hulkdx.findprofessional.core.features.pro.model.ProfessionalReview
import com.hulkdx.findprofessional.core.features.user.Token
import com.hulkdx.findprofessional.core.features.user.User
import com.hulkdx.findprofessional.core.features.user.UserData
import com.hulkdx.findprofessional.core.utils.now
import com.hulkdx.findprofessional.feature.book.time.BookingTimeUiState
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atTime
import kotlinx.datetime.toInstant


fun createProfessional(
    availability: List<ProfessionalAvailability> = listOf(),
    priceNumber: Long = 0,
    priceCurrency: String = "",
) = Professional(
    id = 0,
    email = "",
    firstName = null,
    lastName = null,
    coachType = null,
    priceNumber = priceNumber,
    priceCurrency = priceCurrency,
    profileImageUrl = null,
    rating = null,
    description = null,
    availability = availability,
    reviewSize = "",
    reviews = listOf(),
    createdAt = "",
    updatedAt = ""
)

fun createAvailabilities(
    vararg date: LocalDate,
): List<ProfessionalAvailability> {
    return date.map {
        ProfessionalAvailability(
            id = 1,
            from = it.atTime(LocalTime(1, 1, 1, 1)).toInstant(TimeZone.UTC),
            to = it.atTime(LocalTime(1, 1, 1, 1)).toInstant(TimeZone.UTC),
        )
    }
}

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

fun createBookingTimes(id: Int) = BookingTimeUiState.BookingTime(
    id = id,
    startTime = "mentitum",
    endTime = "interdum",
    type = BookingTimeUiState.BookingTime.Type.UnAvailable,
    date = LocalDate.now(),
)


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

fun newUserData(accessToken: String = "non empty", refreshToken: String = "non empty") = UserData(
    Token(accessToken, refreshToken),
    newUser()
)
