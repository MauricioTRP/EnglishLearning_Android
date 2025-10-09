package com.kotlinpl.english_learning.common.data.local_storage

import androidx.datastore.core.DataStore
import androidx.datastore.core.IOException
import com.kotlinpl.english_learning.common.domain.OnboardingChecker
import com.kotlinpl.english_learning.common.domain.OnboardingFlag
import com.kotlinpl.english_learning.proto.OnboardingFlagProto
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class OnboardingCheckerImpl @Inject constructor (
    private val onboardingStore: DataStore<OnboardingFlagProto>
): OnboardingChecker {
    override suspend fun getOnboardingFlag(): OnboardingFlag {
        val onboardingFlag = try {
            onboardingStore.data.first()
        } catch (_: IOException) {
            OnboardingFlagProto.getDefaultInstance()
        }

        return OnboardingFlag(
            haveDoneOnboarding = onboardingFlag.hasSeenOnboarding
        )
    }

    override suspend fun updateOnboardingFlag(onboardingFlag: OnboardingFlag) {
        onboardingStore.updateData { currentSetting ->
            currentSetting.toBuilder()
                .setHasSeenOnboarding(onboardingFlag.haveDoneOnboarding)
                .build()
        }
    }
}