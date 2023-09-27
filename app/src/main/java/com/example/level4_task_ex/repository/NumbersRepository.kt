package com.example.level4_task_ex.repository

import android.util.Log
import com.example.level4_task_ex.data.api.NumbersApi
import com.example.level4_task_ex.data.api.NumbersApiService
import com.example.level4_task_ex.data.api.util.Resource
import com.example.level4_task_ex.data.model.Numbers
import com.example.level4_task_ex.ui.theme.screens.TYPE_MATH
import com.example.level4_task_ex.ui.theme.screens.TYPE_TRIVIA
import com.example.level4_task_ex.ui.theme.screens.TYPE_YEAR
import kotlinx.coroutines.time.withTimeout
import kotlinx.coroutines.withTimeout

class NumbersRepository {
    private val numbersApiService: NumbersApiService = NumbersApi.createApi()

    /**
     * suspend function that calls a suspend function from the numbersApi call
     * @return result wrapped in our own Resource sealed class
     */
    suspend fun getRandomNumber(numberType: String) : Resource<Numbers> {
        val apiNumberType = when(numberType) {
            TYPE_MATH -> "math"
            TYPE_TRIVIA -> "trivia"
            TYPE_YEAR -> "year"
            else -> "trivia"
        }

        val response = try {
            withTimeout(5_000) {
                numbersApiService.getRandomNumber(apiNumberType)
            }
        } catch(e: Exception) {
            Log.e("NumbersRepository", e.message ?: "No exception message available")
            return Resource.Error("An unknown error occurred")
        }

        return Resource.Success(response)
    }
}