package com.kotlinpl.english_learning.common.domain

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.google.protobuf.InvalidProtocolBufferException
import com.kotlinpl.english_learning.proto.SessionToken
import java.io.InputStream
import java.io.OutputStream

/**
 * Serializer created to handle SessionToken protobuf class
 *
 * You can find the proto definitions on `app/src/main/proto`
 */
object SessionTokenSerializer : Serializer<SessionToken> {
    override suspend fun readFrom(input: InputStream): SessionToken {
        try {
            return SessionToken.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto", exception)
        }
    }

    override suspend fun writeTo(t: SessionToken, output: OutputStream) {
        t.writeTo(output)
    }

    override val defaultValue: SessionToken = SessionToken.getDefaultInstance()
}