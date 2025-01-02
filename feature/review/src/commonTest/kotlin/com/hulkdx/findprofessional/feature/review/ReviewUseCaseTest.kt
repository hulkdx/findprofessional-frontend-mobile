@file:Suppress("PrivatePropertyName")

package com.hulkdx.findprofessional.feature.review

import com.hulkdx.findprofessional.core.features.pro.model.ProfessionalReview
import com.hulkdx.findprofessional.feature.review.ReviewUseCase.Result.DoNothing
import com.hulkdx.findprofessional.feature.review.ReviewUseCase.Result.Success
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

    private val api = ReviewApiMock()

    @BeforeTest
    fun setUp() {
        sut = ReviewUseCase(api)
    }

    @AfterTest
    fun tearDown() {
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
        api.response = { listOf(createReview()) }
        // Act
        val result1 = sut.findAll(1)
        val result2 = sut.findAll(1)
        // Assert
        assertEquals(true, result1 is Success)
        assertEquals(true, result2 is Success)
        assertEquals(2, api.findAllReviewsCalled)
        assertEquals(2, api.findAllReviewsPage)
    }

    @Test
    fun `if end reaches dont increment page`() = runTest {
        // Arrange
        val endReaches = { listOf<ProfessionalReview>() }
        api.response = { endReaches.invoke() }
        // Act
        sut.findAll(1)
        sut.findAll(1)
        // Assert
        assertEquals(2, api.findAllReviewsCalled)
        assertEquals(1, api.findAllReviewsPage)
    }

    // region mock classes

    private class ReviewApiMock : ReviewApi {
        var response: suspend () -> List<ProfessionalReview> = { TODO() }
        var findAllReviewsCalled = 0
        var findAllReviewsPage = -1000

        // @formatter:off
        override suspend fun findAll(professionalId: Int, page: Int, pageSize: Int):List<ProfessionalReview>  {
            findAllReviewsCalled++
            findAllReviewsPage = page
            return response()
        }
        // @formatter:on
    }

    // endregion
}
