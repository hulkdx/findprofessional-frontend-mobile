@file:Suppress("PrivatePropertyName")

package com.hulkdx.findprofessional.common.feature.review

import com.hulkdx.findprofessional.common.feature.home.ProfessionalApi
import com.hulkdx.findprofessional.common.feature.home.model.ProfessionalReview
import com.hulkdx.findprofessional.common.feature.review.ReviewUseCase.Result.DoNothing
import com.hulkdx.findprofessional.common.feature.review.ReviewUseCase.Result.Success
import com.hulkdx.findprofessional.common.utils.KoinTestUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.withContext
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class ReviewUseCaseTest {

    private lateinit var sut: ReviewUseCase

    private val api = ProApiMock()

    @BeforeTest
    fun setUp() {
        KoinTestUtil.startKoin()
        sut = ReviewUseCase(api)
    }

    @AfterTest
    fun tearDown() {
        KoinTestUtil.stopKoin()
    }

    @Test
    fun `findAll should respond api result`() = runTest {
        // Arrange
        api.response = { listOf() }
        // Act
        val result = sut.findAll(1)
        // Assert
        assertEquals(true, result is Success)
    }

    @Test
    fun `if findAll is loading it should not respond api result`() = runTest {
        // Arrange
        val delayTimeout = 100L
        api.response = {
            withContext(Dispatchers.IO) {
                delay(delayTimeout)
                listOf()
            }
        }
        // Act
        val resultDeferred1 = async { sut.findAll(1) }
        val resultDeferred2 = async { sut.findAll(1) }
        val result1 = resultDeferred1.await()
        val result2 = resultDeferred2.await()
        // Assert
        assertEquals(DoNothing, result2)
        assertEquals(true, result1 is Success)
        assertEquals(1, api.findAllReviewsCalled)
        assertEquals(1, api.findAllReviewsPage)
    }

    @Test
    fun `if two findAll calls sequentially then page should be two`() = runTest {
        // Arrange
        api.response = { listOf() }
        // Act
        val result1 = sut.findAll(1)
        val result2 = sut.findAll(1)
        // Assert
        assertEquals(true, result1 is Success)
        assertEquals(true, result2 is Success)
        assertEquals(2, api.findAllReviewsCalled)
        assertEquals(2, api.findAllReviewsPage)
    }

    // region mock classes

    private class ProApiMock : ProfessionalApi {
        var response: suspend () -> List<ProfessionalReview> = { TODO() }
        var findAllReviewsCalled = 0
        var findAllReviewsPage = -1000

        // @formatter:off
        override suspend fun findAll() = TODO()
        override suspend fun findAllReviews(professionalId: Int, page: Int, pageSize: Int):List<ProfessionalReview>  {
            findAllReviewsCalled++
            findAllReviewsPage = page
            return response()
        }
        // @formatter:on
    }

    // endregion
}
