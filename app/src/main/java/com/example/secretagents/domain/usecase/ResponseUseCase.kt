package com.example.secretagents.domain.usecase

import android.util.Log
import com.example.secretagents.data.model.common.KError
import com.example.secretagents.data.model.common.ResultWrapper
import com.example.secretagents.domain.model.GetResponseDataForUI
import com.example.secretagents.domain.repository.ResponseRepo
import com.google.gson.Gson
import javax.inject.Inject

class ResponseUseCase @Inject constructor(private val repository: ResponseRepo) {

    /*operator fun invoke(): Flow<ResultWrapper<GetResponseDataForUI>> = flow {
        try {
            emit(ResultWrapper.Loading)
            val data = repository.getResponseData()
            emit(ResultWrapper.Success(data))

        } catch (e: IOException) {
            Log.e("TAG", "invoke: " + e.message)
        }

    }*/

    suspend operator fun invoke(): ResultWrapper<GetResponseDataForUI> {
        return try {
            val response = repository.getResponseData()
            ResultWrapper.Loading
            ResultWrapper.Success(response)
        } catch (e: Exception) {
            ResultWrapper.GenericError(KError(400, e.message ?: "Exception occurred..."))
        }
    }

}