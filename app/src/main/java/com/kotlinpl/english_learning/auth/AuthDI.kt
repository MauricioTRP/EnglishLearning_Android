package com.kotlinpl.english_learning.auth

import com.kotlinpl.english_learning.auth.data.AuthRepositoryImpl
import com.kotlinpl.english_learning.auth.data.service.AuthApiService
import com.kotlinpl.english_learning.auth.domain.AuthRepository
import com.kotlinpl.english_learning.common.data.network.ApiConfig
import com.kotlinpl.english_learning.common.data.network.HttpClientFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AuthDependencies {
    @Binds
    abstract fun bindAuthRepository(authRepositoryImpl: AuthRepositoryImpl) : AuthRepository

    companion object {
        @Provides
        @Singleton
        fun provideApiAuthService(retrofit: Retrofit) : AuthApiService {
            return retrofit.create(AuthApiService::class.java)
        }
    }
}