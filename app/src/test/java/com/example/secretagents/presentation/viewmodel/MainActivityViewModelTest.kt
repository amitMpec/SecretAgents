package com.example.secretagents.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.secretagents.data.model.common.ResultWrapper
import com.example.secretagents.domain.usecase.ResponseUseCase
import com.example.secretagents.presentation.viewmodel.coroutineRule.MainCoroutineRule
import com.example.secretagents.presentation.viewmodel.mockdata.MockData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

@RunWith(JUnit4::class)
class MainActivityViewModelTest {

    private lateinit var viewModel: MainActivityViewModel

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val getResponseDataUseCase: ResponseUseCase = mock(ResponseUseCase::class.java)

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val testCoroutineRule = MainCoroutineRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(testCoroutineRule.testDispatcher)
        viewModel = MainActivityViewModel(getResponseDataUseCase, testCoroutineRule.testDispatcher)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }


    //First way to test the function
    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun getResponseData() = testCoroutineRule.testDispatcher.runBlockingTest {
        //Arrange
        val response = ResultWrapper.Success(MockData.getResponseData())
        `when`(getResponseDataUseCase.invoke()).thenReturn(response)
        //Act
        viewModel.getResponseData()
        //Verify
        assertEquals(response.value, viewModel.responseData.value)
    }

    //Second way to test the function
    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun getResponseData2() = runTest {
        //Arrange
        val response = ResultWrapper.Success(MockData.getResponseData())
        `when`(getResponseDataUseCase.invoke()).thenReturn(response)
        //Act
        viewModel.getResponseData()
        //Wait for the job to complete
        testCoroutineRule.testDispatcher.scheduler.advanceUntilIdle()

        //Verify
        assertEquals(response.value, viewModel.responseData.value)
    }
}