package com.example.secretagents.domain.repository

import com.example.secretagents.domain.model.GetResponseDataForUI


interface ResponseRepo {
    suspend fun getResponseData(): GetResponseDataForUI
}