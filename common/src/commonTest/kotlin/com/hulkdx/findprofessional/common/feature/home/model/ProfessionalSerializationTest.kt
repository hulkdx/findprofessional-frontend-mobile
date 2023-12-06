package com.hulkdx.findprofessional.common.feature.home.model

import com.hulkdx.findprofessional.common.feature.authentication.model.User
import kotlinx.datetime.Clock
import kotlinx.datetime.toInstant
import kotlin.test.Test
import kotlin.test.assertEquals

class ProfessionalReviewTest {

    @Test
    fun testJsonDeserialization() {
        // Arrange
        val expectedFormat = "9 11 2026"
        val updatedAt = "2026-11-09T00:00:00.Z".toInstant()
        // Act
        val sut = ProfessionalReview(
            id = 0,
            user = User(
                email = "",
                firstName = "",
                lastName = "",
                profileImage = null
            ),
            rate = 0,
            contentText = null,
            createdAt = Clock.System.now(),
            updatedAt = updatedAt
        )
        // Assert
        assertEquals(expectedFormat, sut.formattedDate)
    }

}
