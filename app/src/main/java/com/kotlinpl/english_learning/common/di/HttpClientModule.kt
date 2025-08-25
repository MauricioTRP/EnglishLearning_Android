package com.kotlinpl.english_learning.common.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import com.kotlinpl.english_learning.common.data.network.AuthenticatorInterceptor
import com.kotlinpl.english_learning.common.data.network.AuthorizationInterceptor
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Authenticator
import okhttp3.Interceptor
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class HttpClientModule {
    /**
     * AuthenticatorInterceptor
     */
    @Binds
    abstract fun bindsAuthenticator(authenticatorInterceptor: AuthenticatorInterceptor) : Authenticator

    /**
     * Authorization Interceptor
     */
    @Binds
    abstract fun bindsAuthorization(authorizationInterceptor: AuthorizationInterceptor) : Interceptor
}