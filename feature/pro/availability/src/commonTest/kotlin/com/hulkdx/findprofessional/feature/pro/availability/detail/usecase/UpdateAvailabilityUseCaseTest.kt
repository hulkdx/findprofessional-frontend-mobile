package com.hulkdx.findprofessional.feature.pro.availability.detail.usecase

import com.hulkdx.findprofessional.core.features.pro.model.ProfessionalAvailability
import com.hulkdx.findprofessional.core.features.pro.model.request.UpdateAvailabilityItemRequest
import com.hulkdx.findprofessional.core.features.pro.model.request.UpdateAvailabilityRequest
import com.hulkdx.findprofessional.core.features.pro.storage.AvailabilityStorage
import com.hulkdx.findprofessional.core.resources.Res
import com.hulkdx.findprofessional.core.resources.invalidTime
import com.hulkdx.findprofessional.core.utils.toStringOrRes
import com.hulkdx.findprofessional.feature.pro.availability.detail.TimeSlot
import com.hulkdx.findprofessional.libs.common.tests.StubProfessionalApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.time.Instant

class UpdateAvailabilityUseCaseTest {

    private lateinit var sut: UpdateAvailabilityUseCase
    private val api = object : StubProfessionalApi() {
        var applyAvailabilityCalled: UpdateAvailabilityRequest? = null
        override suspend fun updateAvailability(request: UpdateAvailabilityRequest) {
            applyAvailabilityCalled = request
        }
    }

    private val storageMock = StorageMock()

    @BeforeTest
    fun setUp() {
        sut = UpdateAvailabilityUseCase(api, storageMock)
    }

    @Test
    fun `on execute when valid time slots should call api`() = runBlocking {
        data class TestCase(
            val timeSlots: List<TimeSlot>,
            val date: LocalDate,
            val expected: UpdateAvailabilityRequest,
        )

        val testCases = listOf(
            TestCase(
                timeSlots = listOf(
                    TimeSlot(LocalTime.parse("09:00"), LocalTime.parse("11:00")),
                ),
                date = LocalDate(2024, 1, 1),
                expected = UpdateAvailabilityRequest(
                    listOf(
                        UpdateAvailabilityItemRequest(
                            from = Instant.parse("2024-01-01T09:00:00Z"),
                            to = Instant.parse("2024-01-01T09:30:00Z"),
                        ),
                        UpdateAvailabilityItemRequest(
                            from = Instant.parse("2024-01-01T09:30:00Z"),
                            to = Instant.parse("2024-01-01T10:00:00Z"),
                        ),
                        UpdateAvailabilityItemRequest(
                            from = Instant.parse("2024-01-01T10:00:00Z"),
                            to = Instant.parse("2024-01-01T10:30:00Z"),
                        ),
                        UpdateAvailabilityItemRequest(
                            from = Instant.parse("2024-01-01T10:30:00Z"),
                            to = Instant.parse("2024-01-01T11:00:00Z"),
                        ),
                    )
                )
            ),
        )
        for (t in testCases) {
            // Arrange
            // Act
            sut.execute(t.timeSlots, t.date, TimeZone.UTC)
            // Asserts
            assertEquals(api.applyAvailabilityCalled, t.expected)
        }
    }

    @Test
    fun `on execute when invalid time slots should show error`() = runBlocking {
        val testData = listOf(
            listOf(
                TimeSlot(from = LocalTime.parse("00:00"), to = LocalTime.parse("00:00")),
                TimeSlot(from = LocalTime.parse("05:30"), to = LocalTime.parse("00:00")),
            ),
        )
        for (invalidTimeSlots in testData) {
            // Arrange
            val date = LocalDate(2024, 1, 1)
            // Act
            val err = sut.execute(invalidTimeSlots, date, TimeZone.UTC)
            // Asserts
            assertEquals(err, Res.string.invalidTime.toStringOrRes())
        }
    }

    // region mock classes

    private class StorageMock : AvailabilityStorage {
        override fun getFlow(): Flow<List<ProfessionalAvailability>> = flowOf()
        override suspend fun set(value: List<ProfessionalAvailability>) {}
        override suspend fun remove() {}
    }

    // endregion
}
