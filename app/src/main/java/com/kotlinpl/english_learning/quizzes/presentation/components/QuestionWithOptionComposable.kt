package com.kotlinpl.english_learning.quizzes.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kotlinpl.english_learning.quizzes.presentation.QuizzesViewModel
import com.kotlinpl.english_learning.quizzes.domain.QuestionOption
import com.kotlinpl.english_learning.quizzes.domain.Question
import com.kotlinpl.english_learning.ui.common.ErrorScreen
import com.kotlinpl.english_learning.ui.common.LoadingScreen
import com.kotlinpl.english_learning.ui.theme.English_learningTheme

@Composable
fun QuestionWithOptionComposable(
    onSubmitAnswer: () -> Unit,
    mainViewModel: QuizzesViewModel,
    modifier: Modifier = Modifier,
) {
    val questionUiState by mainViewModel.quizUiState.collectAsState()
    var selectedOption by remember { mutableStateOf<QuestionOption?>(null) }

    when {
        // Error Screen and Loading Screens are not implemented yet
        questionUiState.error != null -> ErrorScreen()
        questionUiState.isLoading -> LoadingScreen()
        else -> QuestionWithOption(
            quizQuestion = questionUiState.quizQuestion!!,
            selectedOption = selectedOption,
            onSelectAnswer = { selectedOption = it },
            onSubmitAnswer = onSubmitAnswer,
            modifier = modifier
        )
    }
}

@Composable
private fun QuestionWithOption(
    quizQuestion: Question,
    selectedOption: QuestionOption?,
    onSelectAnswer: (QuestionOption) -> Unit,
    onSubmitAnswer: () -> Unit,
    modifier: Modifier = Modifier
) {
    // Modifier.selectableGroup() is essential to ensure correct accessibility behavior
    Column(modifier = modifier.selectableGroup()) {
        Text(
            text = quizQuestion.text,
            style = MaterialTheme
                .typography
                .headlineMedium,
            modifier = Modifier.padding(16.dp)
        )

        quizQuestion.options?.forEach { quizOption ->
            val isSelected = selectedOption == quizOption

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .padding(horizontal = 16.dp)
                    .selectable(
                        selected = isSelected,
                        onClick = {
                            onSelectAnswer(quizOption)
                        },
                        role = Role.RadioButton
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = isSelected,
                    onClick = null
                )
                Text(
                    text = quizOption.text,
                    style = MaterialTheme
                        .typography
                        .bodyLarge,
                    modifier = Modifier.padding(start = 8.dp)
                )

            }
        }

        // Next Button
        Button(
            onClick = onSubmitAnswer,
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "Submit/Next")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun QuizOptionsPreview() {
    val question = getQuestion()
    val selectedOption = QuestionOption("2", "I am going to sleep.", true)

    English_learningTheme {
            QuestionWithOption(
                quizQuestion = question,
                selectedOption = selectedOption,
                onSelectAnswer = {},
                onSubmitAnswer = {},
                modifier = Modifier
            )
    }
}

private fun getQuestion() : Question {
    return Question(
        id = "1",
        text = "Question with options?",
        options = listOf(
            QuestionOption(id = "1", "option 1.", false),
            QuestionOption("2", "option 2.", true),
            QuestionOption("3", "option 3.", false),
            QuestionOption("4", "option 4.", false)
        ),
        tags = listOf("Easy", "Etiquette", "Moaning")
    )
}