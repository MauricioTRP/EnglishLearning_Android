package com.kotlinpl.english_learning.quizzes.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kotlinpl.english_learning.AppDatabase
import com.kotlinpl.english_learning.quizzes.domain.QuizRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuizzesViewModel @Inject constructor(
    private val quizRepository: QuizRepository,
) : ViewModel() {
    var quizUIState = MutableStateFlow(QuizUiState())
        private set

    init {
        viewModelScope.launch {
            val isSync = quizRepository.sync()
            print("Is sync $isSync")
        }
        getQuizzes()
    }

    fun getQuizzes() {
        viewModelScope.launch {
            quizUIState.value = QuizUiState(isLoading = true)
            quizRepository.getQuizItems().collect {
                quizUIState.value = QuizUiState(quizzes = it)
            }

            val firstQuiz = quizUIState.value.quizzes.firstOrNull()
            quizUIState.value = QuizUiState(
                isLoading = false,
                currentQuiz = firstQuiz
            )
        }
    }

    fun submitQuiz(quizId: Int, answer: List<Int>) {
        quizUIState.value = QuizUiState(isLoading = true)
        viewModelScope.launch {
            quizRepository.submitAnswer(quizId, answer)
        }
        quizUIState.value = QuizUiState(isLoading = false)
        updateCurrentQuiz(quizId.toString())
    }

    private fun updateCurrentQuiz(quizId: String) {
        val quizzes = quizUIState.value.quizzes
        val currentQuiz = quizzes.firstOrNull { it.id == quizId }

        quizUIState.value = quizUIState.value.copy(
            currentQuiz = currentQuiz
        )
    }
}
