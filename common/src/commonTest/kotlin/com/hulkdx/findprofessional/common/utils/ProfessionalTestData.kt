package com.hulkdx.findprofessional.common.utils

import com.hulkdx.findprofessional.common.feature.authentication.model.User
import com.hulkdx.findprofessional.common.feature.book.BookUiState.BookingTime
import com.hulkdx.findprofessional.common.feature.home.model.Professional
import com.hulkdx.findprofessional.common.feature.home.model.ProfessionalAvailability
import com.hulkdx.findprofessional.common.feature.home.model.ProfessionalReview
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate


fun createProfessional(
    availability: List<ProfessionalAvailability> = listOf(),
) = Professional(
    id = 0,
    email = "",
    firstName = null,
    lastName = null,
    coachType = null,
    priceNumber = null,
    priceCurrency = null,
    profileImageUrl = null,
    rating = null,
    description = null,
    availability = availability,
    reviewSize = "",
    reviews = listOf(),
    createdAt = "",
    updatedAt = ""
)

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

fun createBookingTimes(id: Int) = BookingTime(
    id = id,
    startTime = "mentitum",
    endTime = "interdum",
    type = BookingTime.Type.UnAvailable,
    date = LocalDate.now(),
)
