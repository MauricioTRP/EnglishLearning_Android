package com.kotlinpl.english_learning.common.domain

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.google.protobuf.InvalidProtocolBufferException
import com.kotlinpl.english_learning.proto.AuthTokensProto
import java.io.InputStream
import java.io.OutputStream

/**
 * Serializer created to handle AuthTokensProto protobuf class
 *
 * You can find the proto definitions on `app/src/main/proto`
 * If you need further reading check [Simonas' articles](https://medium.com/androiddevelopers/all-about-proto-datastore-1b1af6cd2879)
 * at Medium
 *
 * > Be aware that `AuthTokensProto` class will only be available after protobuf compilation runs.
 * > So you may see some error display before building the app
 */
object AuthTokensProtoSerializer : Serializer<AuthTokensProto> {
    override suspend fun readFrom(input: InputStream): AuthTokensProto {
        try {
            return AuthTokensProto.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto", exception)
        }
    }

    override suspend fun writeTo(t: AuthTokensProto, output: OutputStream) {
        t.writeTo(output)
    }

    override val defaultValue: AuthTokensProto = AuthTokensProto.getDefaultInstance()
}