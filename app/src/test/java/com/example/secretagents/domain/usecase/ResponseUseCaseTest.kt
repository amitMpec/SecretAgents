package com.example.secretagents.domain.usecase

import com.example.secretagents.data.model.common.ResultWrapper
import com.example.secretagents.domain.repository.ResponseRepo
import com.example.secretagents.presentation.viewmodel.mockdata.MockData
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class ResponseUseCaseTest {

    private lateinit var responseUseCase: ResponseUseCase

    private val responseRepo: ResponseRepo = Mockito.mock(ResponseRepo::class.java)

    @Before
    fun setUp() {
        responseUseCase = ResponseUseCase(responseRepo)
    }

    @After
    fun tearDown() {
    }

    @Test
    operator fun invoke() = runTest {

        //Arrange
        val response = MockData.getResponseData()
        Mockito.`when`(responseRepo.getResponseData()).thenReturn(response)

        //Act
        val result = responseUseCase.invoke()

        //Verify
        assertTrue(result is ResultWrapper.Success)
        assertEquals(500, (result as ResultWrapper.Success).value.data.totalItems)
    }
}