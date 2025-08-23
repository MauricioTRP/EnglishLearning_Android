package com.kotlinpl.english_learning.common.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import com.kotlinpl.english_learning.common.data.local_storage.TokenProviderImpl
import com.kotlinpl.english_learning.common.domain.AuthTokensProtoSerializer
import com.kotlinpl.english_learning.common.domain.TokenProvider
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.kotlinpl.english_learning.proto.AuthTokensProto
import dagger.Binds
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataDependencies {
    /**
     * Binding between `TokenProvider` and `TokenProviderImpl`
     */
    @Binds
    @Singleton
    abstract fun bindTokenProvider(impl: TokenProviderImpl) : TokenProvider

    companion object {
        /**
         * proto stores reading from .proto files
         */
        private val Context.sessionTokenStore: DataStore<AuthTokensProto> by dataStore<AuthTokensProto>(
            fileName = "session_token.pb",
            serializer = AuthTokensProtoSerializer
        )

        /**
         * Dependency provider for Session Token DataStore
         */
        @Provides
        @Singleton
        fun provideSessionTokenStore(
            @ApplicationContext context: Context
        ): DataStore<AuthTokensProto> {
            return context.sessionTokenStore
        }
    }
}