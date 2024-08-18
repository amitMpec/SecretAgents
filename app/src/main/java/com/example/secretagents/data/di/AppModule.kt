package com.example.secretagents.data.di

import com.example.secretagents.data.remote.AppApi
import com.example.secretagents.data.repositoryImpl.ResponseRepoImpl
import com.example.secretagents.domain.repository.ResponseRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun apiManager(apiService: AppApi): ResponseRepo {
        return ResponseRepoImpl(apiService)
    }

}