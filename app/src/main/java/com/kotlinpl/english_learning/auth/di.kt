package com.kotlinpl.english_learning.auth

import com.kotlinpl.english_learning.auth.data.AuthRepositoryImpl
import com.kotlinpl.english_learning.auth.domain.AuthRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class di {
    @Binds
    abstract fun bindAuthRepository(authRepositoryImpl: AuthRepositoryImpl) : AuthRepository
}