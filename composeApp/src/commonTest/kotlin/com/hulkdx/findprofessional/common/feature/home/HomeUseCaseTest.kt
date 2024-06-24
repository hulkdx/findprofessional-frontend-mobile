@file:Suppress("PrivatePropertyName")

package com.hulkdx.findprofessional.common.feature.home

import com.hulkdx.findprofessional.common.feature.home.model.Professional
import com.hulkdx.findprofessional.common.feature.home.model.ProfessionalReview
import com.hulkdx.findprofessional.common.utils.KoinTestUtil
import com.hulkdx.findprofessional.common.utils.createProfessional
import kotlinx.coroutines.test.runTest
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class HomeUseCaseTest {

    private lateinit var sut: HomeUseCase

    private val api = ProApiMock()

    @BeforeTest
    fun setUp() {
        KoinTestUtil.startKoin()
        sut = HomeUseCase(api)
    }

    @AfterTest
    fun tearDown() {
        KoinTestUtil.stopKoin()
    }

    @Test
    fun `findAll should respond api result`() = runTest {
        // Arrange
        val apiResult = listOf(createProfessional(), createProfessional())
        api.findAllResponse = apiResult
        // Act
        val (result, err) = sut.findAll()
        // Assert
        assertEquals(err, null)
        assertEquals(result, apiResult)
    }

    // region mock classes

    private class ProApiMock : ProfessionalApi {
        var findAllResponse: List<Professional> = listOf()

        // @formatter:off
        override suspend fun findAll() = findAllResponse
        override suspend fun findAllReviews(professionalId: Int, page: Int, pageSize: Int): List<ProfessionalReview> = TODO()
        // @formatter:on
    }

    // endregion
}
