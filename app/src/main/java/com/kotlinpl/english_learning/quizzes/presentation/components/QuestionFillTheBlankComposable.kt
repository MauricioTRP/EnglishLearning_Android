package com.kotlinpl.english_learning.quizzes.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun QuestionFillTheBlankComposable(text: String, answer: String) {
    val position = text.indexOf(answer)

    if (position == -1) {
        Text(text)
        return
    }

    Text(
        text = AnnotatedString(
            text = text,
            spanStyles = listOf(
                AnnotatedString.Range(
                    SpanStyle(
                        color = Color.Blue,
                        fontStyle = FontStyle.Italic
                    ),
                    start = position,
                    end = position + answer.length
                )
            )
        )
    )
}

@Preview(showBackground = true)
@Composable
fun QuestionFillTheBlankPreview() {
    QuestionFillTheBlankComposable(
        text = "I am going to sleep as a dormouse",
        answer = "sleep"

    )
}

@Composable
fun FillTheBlankQuestion(
    text: String,
    correctAnswer: String,
    onAnswerSubmitted: (isCorrect: Boolean) -> Unit
) {
    var userAnswer by remember { mutableStateOf("") }
    val position = text.indexOf(correctAnswer, ignoreCase = true)

    if (position == -1) {
        Text("Error: The answer word is not found in the provided text.")
        return
    }

    val textBefore = text.substring(0, position)
    val textAfter = text.substring(position + correctAnswer.length)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = textBefore)
            OutlinedTextField(
                value = userAnswer,
                onValueChange = { userAnswer = it },
                modifier = Modifier.width(120.dp),
                singleLine = true,
                label = { Text("Your answer") }
            )
            Text(text = textAfter)
        }
        Spacer(Modifier.height(16.dp))
        Button(onClick = {
            onAnswerSubmitted(userAnswer.equals(correctAnswer, ignoreCase = true))
        }) {
            Text("Check Answer")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FillTheBlankQuestionPreview() {
    FillTheBlankQuestion(
        text = "I am going to sleep as a dormouse",
        correctAnswer = "sleep",
        onAnswerSubmitted = { isCorrect ->
            println("Answer is correct: $isCorrect")
        }
    )
}