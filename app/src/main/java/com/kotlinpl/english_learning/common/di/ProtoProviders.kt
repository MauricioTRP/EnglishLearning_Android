package com.kotlinpl.english_learning.common.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import com.kotlinpl.english_learning.common.domain.SessionTokenSerializer
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.kotlinpl.english_learning.proto.SessionToken
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ProtoProviders {
    /**
     * proto stores reading from .proto files
     */
    private val Context.sessionTokenStore : DataStore<SessionToken> by dataStore<SessionToken>(
        fileName = "session_token.pb",
        serializer = SessionTokenSerializer
    )

    /**
     * Dependency provider for Session Token DataStore
     */
    @Provides
    @Singleton
    fun provideSessionTokenStore(
        @ApplicationContext context: Context
    ) : DataStore<SessionToken> {
        return context.sessionTokenStore
    }
}