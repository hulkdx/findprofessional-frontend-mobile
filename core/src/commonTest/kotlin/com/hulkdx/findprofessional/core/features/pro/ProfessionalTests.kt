package com.hulkdx.findprofessional.core.features.pro

import com.hulkdx.findprofessional.core.features.user.User
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlin.test.Test
import kotlin.test.assertEquals


class ProfessionalTests {
    @Test
    fun `formattedDate tests`() {
        // Arrange
        val expectedFormat = "9 11 2026"
        val updatedAt = Instant.parse("2026-11-09T00:00:00Z")
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
