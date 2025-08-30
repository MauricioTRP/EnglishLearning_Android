package com.kotlinpl.english_learning.auth.data

import android.util.Log
import com.kotlinpl.english_learning.auth.data.dto.LoginRequestDto
import com.kotlinpl.english_learning.auth.data.dto.LoginResponseDto
import com.kotlinpl.english_learning.auth.data.dto.RegisterRequestDto
import com.kotlinpl.english_learning.auth.data.dto.RegisterResponseDto
import com.kotlinpl.english_learning.auth.data.service.AuthApiService
import com.kotlinpl.english_learning.auth.domain.AuthRepository
import javax.inject.Inject

const val TAG = "AuthRepositoryImpl"

class AuthRepositoryImpl @Inject constructor (
    private val authApiService: AuthApiService
) : AuthRepository {
    override suspend fun login(
        email: String,
        password: String
    ): Result<LoginResponseDto> {
        /**
         * "Map" the actual response from retrofit, to a usable type of result
         * being used in Presentation layer
         */
        return try {
            val response = authApiService.login(
                LoginRequestDto(email = email, password = password)
            )

            if (response.isSuccessful) {
                response.body()?.let { loginData ->
                    Log.e(TAG, loginData.toString())
                    Result.success(loginData) // loginData is non-null here
                } ?: Result.failure(Exception("Login successful but response body was null."))
            } else {
                val errorMsg =
                    "Login failed with code: ${response.code()}, message: ${response.message()}"
                Log.e(TAG, errorMsg)
                Result.failure(Exception(errorMsg))
            }
        } catch (e: Exception) {
            Log.e(TAG, "Login operation failed: ${e.message}", e)
            Result.failure(e)
        }
    }

    override suspend fun register(
        email: String,
        password: String
    ) : Result<RegisterResponseDto>  {
        return try {
            val response = authApiService.register(
                RegisterRequestDto(email = email, password = password)
            )

            if (response.isSuccessful) {
                response.body()?.let { registerData ->
                    Log.d(TAG, registerData.toString())
                    Result.success(registerData)
                } ?: Result.failure(Exception("Register was successful, but response body was null"))
            } else {
                val errorMsg =
                    "Register failed with code: ${response.code()}, message: ${response.message()}"

                Result.failure(Exception(errorMsg))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
