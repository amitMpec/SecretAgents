package com.example.secretagents.data.remote

import com.example.secretagents.domain.model.GetResponseDataForUI
import retrofit2.http.GET

interface AppApi {
    @GET("randomusers?page=1&limit=20")
    suspend fun getResponseData(): GetResponseDataForUI
}

