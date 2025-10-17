package com.kotlinpl.english_learning.quizzes.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.kotlinpl.english_learning.quizzes.presentation.components.QuestionWithOptionComposable

//import com.kotlinpl.english_learning.quizzes.presentation.components.QuestionWithOptionComposable

@Composable
fun SingleQuizScreen(
    viewModel: QuizzesViewModel,
    quizId: String,
    onSubmitAnswer: () -> Unit,
    modifier: Modifier
) {
    QuestionWithOptionComposable(
        onSubmitAnswer = onSubmitAnswer,
        viewModel = viewModel,
        quizId = quizId,
        modifier = modifier
    )
}