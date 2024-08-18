package com.example.secretagents.data.repositoryImpl

import com.example.secretagents.data.remote.AppApi
import com.example.secretagents.domain.model.GetResponseDataForUI
import com.example.secretagents.domain.repository.ResponseRepo

class ResponseRepoImpl(val appApi: AppApi) : ResponseRepo {
    override suspend fun getResponseData(): GetResponseDataForUI {
        return appApi.getResponseData()
    }
}