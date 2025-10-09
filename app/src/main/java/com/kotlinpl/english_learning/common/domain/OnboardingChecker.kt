package com.kotlinpl.english_learning.common.domain

/**
 * Manage async reading of onboarding proto datastore
 */
interface OnboardingChecker {
    suspend fun getOnboardingFlag() : OnboardingFlag
    suspend fun updateOnboardingFlag(onboardingFlag: OnboardingFlag)
}