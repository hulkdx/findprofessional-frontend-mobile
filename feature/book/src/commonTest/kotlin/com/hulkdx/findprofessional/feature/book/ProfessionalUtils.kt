package com.hulkdx.findprofessional.feature.book

import com.hulkdx.findprofessional.core.model.pro.Professional
import com.hulkdx.findprofessional.core.model.pro.ProfessionalAvailability
import com.hulkdx.findprofessional.core.model.pro.ProfessionalReview
import com.hulkdx.findprofessional.core.model.user.User
import com.hulkdx.findprofessional.core.utils.now
import com.hulkdx.findprofessional.feature.book.time.BookingTimeUiState
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate


fun createProfessional(
    availability: List<ProfessionalAvailability> = listOf(),
    priceNumber: Int = 0,
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

