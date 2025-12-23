@file:Suppress("PrivatePropertyName")
@file:OptIn(ExperimentalCoroutinesApi::class)

package com.hulkdx.findprofessional.feature.home.main

import com.hulkdx.findprofessional.feature.pro.model.Professional
import com.hulkdx.findprofessional.libs.common.tests.StubNavigator
import com.hulkdx.findprofessional.libs.common.tests.StubProfessionalApi
import com.hulkdx.findprofessional.libs.common.tests.createProfessional
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class HomeViewModelTest {

    private lateinit var sut: HomeViewModel

    private val api = object : StubProfessionalApi() {
        var findAllResponse: List<Professional> = listOf()

        override suspend fun findAll() = findAllResponse
    }

    @BeforeTest
    fun setUp() {
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
    }

    private fun createSut() {
        sut = HomeViewModel(
            ProfessionalUseCase(api),
            StubNavigator(),
        )
    }

    @Test
    fun `createSut should respond api result`() = runTest {
        // Arrange
        val apiResult = listOf(createProfessional(), createProfessional())
        api.findAllResponse = apiResult
        // Act
        createSut()
        // Assert
        val (err, _) = sut.error.first()
        assertEquals(err, null)
        val result = sut.professionals.first()
        assertEquals(result, apiResult)
    }
}
