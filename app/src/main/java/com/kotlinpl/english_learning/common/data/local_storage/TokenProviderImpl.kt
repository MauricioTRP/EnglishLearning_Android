package com.kotlinpl.english_learning.common.data.local_storage

import androidx.datastore.core.DataStore
import androidx.datastore.core.IOException
import com.kotlinpl.english_learning.common.domain.AuthTokens
import com.kotlinpl.english_learning.common.domain.TokenProvider
import com.kotlinpl.english_learning.proto.AuthTokensProto // Generated Class from auth_tokens.proto compiling
import kotlinx.coroutines.flow.first

/**
 * Token Provider implementation
 *
 * It'll work as the main class to Write and Read data from the Proto Datastore <AuthToken>
 */
class TokenProviderImpl(
    private val tokenStore: DataStore<AuthTokensProto> // Same name as `Message` in `proto/auth_tokens.proto`
) : TokenProvider {
    override suspend fun getToken() : AuthTokens {
        val sessionToken = try {
            tokenStore.data.first()
        } catch (_: IOException) {
            AuthTokensProto.getDefaultInstance()
        }
        return AuthTokens(
            accessToken = sessionToken.accessToken,
            refreshToken = sessionToken.refreshToken
        )
    }

    override suspend fun setToken(authTokens: AuthTokens) {
        tokenStore.updateData { currentSettings ->
            currentSettings.toBuilder()
                .setAccessToken(authTokens.accessToken)
                .setRefreshToken(authTokens.refreshToken)
                .build()
        }
    }
}