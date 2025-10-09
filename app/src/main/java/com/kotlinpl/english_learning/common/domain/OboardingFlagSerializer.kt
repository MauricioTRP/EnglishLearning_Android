package com.kotlinpl.english_learning.common.domain

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.google.protobuf.InvalidProtocolBufferException
import com.kotlinpl.english_learning.proto.OnboardingFlagProto
import java.io.InputStream
import java.io.OutputStream

/**
 * Serializer created to handle OnboardingFlagProto protobuf class
 *
 * > Be aware that `OnboardingFlagProto` class will only be available
 * > after compiling
 */
object OnboardingFlagSerializer : Serializer<OnboardingFlagProto> {
    override suspend fun readFrom(input: InputStream): OnboardingFlagProto {
        try {
            return OnboardingFlagProto.parseFrom(input)
        } catch (e: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read onboarding flag proto")
        }
    }

    override suspend fun writeTo(t: OnboardingFlagProto, output: OutputStream) {
        t.writeTo(output)
    }

    override val defaultValue: OnboardingFlagProto = OnboardingFlagProto.getDefaultInstance()
}