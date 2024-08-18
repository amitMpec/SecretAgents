package com.example.secretagents.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.secretagents.data.di.IoDispatcher
import com.example.secretagents.data.model.common.ResultWrapper
import com.example.secretagents.domain.model.GetResponseDataForUI
import com.example.secretagents.domain.usecase.ResponseUseCase
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val responseUseCase: ResponseUseCase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val TAG = "MainActivityViewModel"

    private val _responseData = MutableLiveData<GetResponseDataForUI>()
    val responseData: LiveData<GetResponseDataForUI> get() = _responseData
    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    fun getResponseData() {
        viewModelScope.launch(ioDispatcher) {

            when (val response = responseUseCase.invoke()) {

                is ResultWrapper.Loading -> {
                    _loading.postValue(true)
                    Log.e(TAG, "getResponse : loading")
                }

                is ResultWrapper.Success -> {
                    _loading.postValue(false)
                    _responseData.postValue(response.value)
                    val responseJson = Gson().toJson(response.value)
                    Log.e(TAG, "getResponse : $responseJson")
                }

                else -> {
                    _loading.postValue(false)
                    Log.e(TAG, "getResponse : No value received")
                }
            }
        }
    }

}