package com.kotlinpl.english_learning.onboarding

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun OnboardingScreen(
    onEndOnboarding: () -> Unit,
    modifier: Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = "Welcome to the Basics Codelab!",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier
                .padding(vertical = 24.dp)
                .align(Alignment.CenterHorizontally)
        )

        Text("This is the first onboarding screen")

        Text("You can add more screens if you need, defining them as Composables functions, and adding them to the navigation graph")

        Button(
            onClick = onEndOnboarding,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("End onboarding")
        }

    }
}