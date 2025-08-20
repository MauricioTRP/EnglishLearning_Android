package com.kotlinpl.english_learning.auth

import com.kotlinpl.english_learning.auth.data.AuthRepositoryImpl
import com.kotlinpl.english_learning.auth.data.service.AuthApiService
import com.kotlinpl.english_learning.auth.domain.AuthRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AuthDependencies {
    @Binds
    abstract fun bindAuthRepository(authRepositoryImpl: AuthRepositoryImpl) : AuthRepository

    companion object {
        @Provides
        @Singleton
        fun providesRetrofitInstance() : Retrofit {

            return Retrofit.Builder()
                .baseUrl("http://localhost:3000/v1/")
                .build()
        }

        @Provides
        @Singleton
        fun provideApiAuthService(retrofit: Retrofit) : AuthApiService {
            return retrofit.create(AuthApiService::class.java)
        }
    }
}