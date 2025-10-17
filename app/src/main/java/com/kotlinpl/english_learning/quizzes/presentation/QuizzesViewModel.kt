package com.kotlinpl.english_learning.quizzes.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kotlinpl.english_learning.quizzes.domain.QuizRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuizzesViewModel @Inject constructor(
    private val quizRepository: QuizRepository,
) : ViewModel() {
    private val _quizUIState = MutableStateFlow(QuizUiState())
    val quizUIState: StateFlow<QuizUiState> = _quizUIState.asStateFlow()

    init {
        viewModelScope.launch {
            try {
                quizRepository.sync()
            } catch (e: Exception) {
                e.printStackTrace()
                _quizUIState.value = _quizUIState.value.copy(
                    error = e.message
                )
            }
        }
        getQuizzes()
    }

    fun getQuizzes() {
        viewModelScope.launch {
            _quizUIState.value = _quizUIState.value.copy(
                isLoading = true
            )
            quizRepository.getQuizItems().collect {
                _quizUIState.value = _quizUIState.value.copy(
                    quizzes = it,
                    isLoading = false // change loading state at first quiz being loaded
                )

                Log.d("QuizzesViewModel", "getQuizzes: $it")
            }
        }
    }

    fun submitQuiz(quizId: String, answer: List<Int>) {
        _quizUIState.value = _quizUIState.value.copy(
            isLoading = true
        )
        viewModelScope.launch {
            quizRepository.submitAnswer(quizId, answer)
        }
        _quizUIState.value = _quizUIState.value.copy(isLoading = false)
        updateCurrentQuiz(quizId)
    }

    fun updateCurrentQuiz(quizId: String) {
        val quizzes = _quizUIState.value.quizzes
        val currentQuiz = quizzes.firstOrNull { it.id == quizId }

        _quizUIState.value = _quizUIState.value.copy(
            currentQuiz = currentQuiz
        )
    }
}