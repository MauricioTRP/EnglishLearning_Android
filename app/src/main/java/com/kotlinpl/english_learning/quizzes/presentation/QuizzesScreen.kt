package com.kotlinpl.english_learning.quizzes.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.kotlinpl.english_learning.quizzes.presentation.components.QuestionWithOptionComposable

@Composable
fun QuizzesScreen(
    viewModel: QuizzesViewModel,
    modifier: Modifier
) {
    QuestionWithOptionComposable(
        onSubmitAnswer = {  },
        mainViewModel = viewModel,
        modifier = modifier
    )
}