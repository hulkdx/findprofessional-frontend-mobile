@file:Suppress("PrivatePropertyName")

package com.hulkdx.findprofessional.common.feature.home

import com.hulkdx.findprofessional.common.utils.KoinTestUtil
import kotlinx.coroutines.test.runTest
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class HomeUseCaseTest {

    private val TEST_PRO = Professional(
        1,
        "",
        "",
        "",
        "",
        1,
    )

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
        val apiResult = listOf(TEST_PRO, TEST_PRO)
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

        override suspend fun findAll(): List<Professional> {
            return findAllResponse
        }
    }

    // endregion
}
