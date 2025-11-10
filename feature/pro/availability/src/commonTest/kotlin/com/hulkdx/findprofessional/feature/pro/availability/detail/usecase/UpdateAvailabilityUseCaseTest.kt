package com.hulkdx.findprofessional.feature.pro.availability.detail.usecase

import com.hulkdx.findprofessional.core.features.pro.api.ProfessionalApi
import com.hulkdx.findprofessional.core.features.pro.model.ProfessionalAvailability
import com.hulkdx.findprofessional.core.features.pro.model.request.SignUpProRequest
import com.hulkdx.findprofessional.core.features.pro.model.request.UpdateAvailabilityItemRequest
import com.hulkdx.findprofessional.core.features.pro.model.request.UpdateAvailabilityRequest
import com.hulkdx.findprofessional.core.features.pro.storage.AvailabilityStorage
import com.hulkdx.findprofessional.core.features.user.ProUser
import com.hulkdx.findprofessional.core.resources.Res
import com.hulkdx.findprofessional.core.resources.invalidTime
import com.hulkdx.findprofessional.core.utils.toStringOrRes
import com.hulkdx.findprofessional.feature.pro.availability.detail.TimeSlot
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class UpdateAvailabilityUseCaseTest {

    private lateinit var sut: UpdateAvailabilityUseCase
    private val api = ProApiMock()

    private val storageMock = StorageMock()

    @BeforeTest
    fun setUp() {
        sut = UpdateAvailabilityUseCase(api, storageMock)
    }

    @Test
    fun `on execute when valid time slots should call api`() = runBlocking {
        // Arrange
        val timeSlots = listOf(
            TimeSlot(LocalTime.parse("09:00"), LocalTime.parse("10:00")),
            TimeSlot(LocalTime.parse("10:30"), LocalTime.parse("11:30")),
        )
        val date = LocalDate(2024, 1, 1)
        val expected = UpdateAvailabilityRequest(
            listOf(
                UpdateAvailabilityItemRequest("2024-01-01", "09:00:00", "10:00:00"),
                UpdateAvailabilityItemRequest("2024-01-01", "10:30:00", "11:30:00"),
            )
        )
        // Act
        sut.execute(timeSlots, date)
        // Asserts
        assertEquals(api.applyAvailabilityCalled, expected)
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
            val err = sut.execute(invalidTimeSlots, date)
            // Asserts
            assertEquals(err, Res.string.invalidTime.toStringOrRes())
        }
    }

    // region mock classes

    private class ProApiMock : ProfessionalApi {
        var applyAvailabilityCalled: UpdateAvailabilityRequest? = null

        override suspend fun findAll() = throw RuntimeException("")
        override suspend fun register(request: SignUpProRequest) = throw RuntimeException("")
        override suspend fun update(proUser: ProUser) {}
        override suspend fun getAvailability(): List<ProfessionalAvailability> = listOf()
        override suspend fun updateAvailability(request: UpdateAvailabilityRequest) {
            applyAvailabilityCalled = request
        }
    }

    private class StorageMock: AvailabilityStorage {
        override fun getFlow(): Flow<List<ProfessionalAvailability>> = flowOf()
        override suspend fun set(value: List<ProfessionalAvailability>) {}
        override suspend fun remove() {}
    }

    // endregion
}
