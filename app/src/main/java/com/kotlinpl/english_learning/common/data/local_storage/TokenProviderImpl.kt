package com.kotlinpl.english_learning.common.data.local_storage

import androidx.datastore.core.DataStore
import androidx.datastore.core.IOException
import com.kotlinpl.english_learning.common.domain.TokenProvider
import com.kotlinpl.english_learning.proto.SessionToken // Generated Class from session_token.proto compiling
import kotlinx.coroutines.flow.first

class TokenProviderImpl(
    private val tokenStore: DataStore<SessionToken> // Same name as `Message` in `proto/session_token.proto`
) : TokenProvider {
    override suspend fun getToken(): String {
        val sessionToken = try {
            tokenStore.data.first()
        } catch (_: IOException) {
            SessionToken.getDefaultInstance()
        }

        return sessionToken.token
    }

    override suspend fun setToken(token: String) {
        try {
//            tokenStore.updateData{  }
        } catch (_: IOException) {

        }
    }
}