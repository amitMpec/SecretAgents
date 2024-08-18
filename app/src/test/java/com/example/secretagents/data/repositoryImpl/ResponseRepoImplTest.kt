package com.example.secretagents.data.repositoryImpl

import com.example.secretagents.data.remote.AppApi
import com.example.secretagents.presentation.viewmodel.mockdata.MockData
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class ResponseRepoImplTest {

    private lateinit var responseRepoImpl: ResponseRepoImpl
    private val appApi: AppApi = Mockito.mock(AppApi::class.java)

    @Before
    fun setUp() {
        responseRepoImpl = ResponseRepoImpl(appApi)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun getResponseData() = runTest {
        //Arrange
        val getResponse = MockData.getResponseData()
        Mockito.`when`(appApi.getResponseData()).thenReturn(getResponse)
        //Act
        val response = responseRepoImpl.getResponseData()
        //Verify
        assertEquals(500, response.data.totalItems)
    }

}