package com.kotlinpl.english_learning.quizzes.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kotlinpl.english_learning.AppDatabase
import com.kotlinpl.english_learning.quizzes.data.room.mappers.toDomain
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuizzesViewModel @Inject constructor(
    private val appDatabase: AppDatabase
) : ViewModel() {
    private val _quizUiState = MutableStateFlow(QuestionUiState())
    val quizUiState : StateFlow<QuestionUiState> = _quizUiState
    val questionDao = appDatabase.questionDao()

    init {
        viewModelScope.launch {
            questionWithTags()
        }
    }

    fun questionWithTags() {
        viewModelScope.launch {
            try {
                val questions = questionDao.getQuestionsByTags(listOf("Geography", "Astronomy"), 10).map { it.toDomain() }

                _quizUiState.value = _quizUiState.value.copy(
                    quizQuestion = questions.first(),
                    isLoading = false
                )
            } catch (e: Exception) {
                _quizUiState.value = quizUiState.value.copy(
                    error = e.message,
                    isLoading = false
                )
            }
        }
    }
}