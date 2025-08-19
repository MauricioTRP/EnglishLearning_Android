package com.kotlinpl.english_learning.common.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicSecureTextField
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.TextObfuscationMode
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kotlinpl.english_learning.R

@Composable
fun PasswordTextField(
    state: TextFieldState,
    isPasswordVisible: Boolean,
    onTogglePasswordVisibility: () -> Unit,
    hint: String,
    title: String?,
    modifier: Modifier = Modifier
) {
    var isFocused by remember {
        mutableStateOf(false)
    }

    Column(
        modifier =  modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            if (title != null) {
                Text(
                    text = title,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }

        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.spacing_xs))) /* TODO: Create space .dp standard measures */

        BasicSecureTextField(
            state = state,
            textObfuscationMode = if (isPasswordVisible) {
                TextObfuscationMode.Visible
            } else TextObfuscationMode.Hidden,
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .background(MaterialTheme.colorScheme.primaryContainer)
                .onFocusChanged {
                    isFocused = it.isFocused
                },
            decorator = { innerBox ->
                Row(
                    modifier = Modifier
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = stringResource(R.string.email_description),
                        tint = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                    Spacer(Modifier.width(dimensionResource(R.dimen.spacing_m)))
                    Box(
                        modifier = Modifier
                            .weight(1f)
                    ) {
                        /* Shows hint if input text is empty, and text fiels isn't focused */
                        if (state.text.isEmpty() && !isFocused) {
                            Text(
                                text = hint,
                                modifier = Modifier
                                    .fillMaxWidth(),
                                color = MaterialTheme
                                    .colorScheme
                                    .onPrimaryContainer
                                    .copy(
                                        alpha = 0.4f
                                    )
                            )
                        }

                        innerBox() /* Handles text input */
                    }
                    Spacer(Modifier.width(dimensionResource(R.dimen.spacing_m)))
                    IconButton(onClick = onTogglePasswordVisibility) {
                        Icon(
                            imageVector = if (isPasswordVisible) {
                                EyeClosed
                            } else EyeOpen,
                            contentDescription = if (isPasswordVisible) {
                                stringResource(R.string.hide_password)
                            } else stringResource(R.string.show_password),
                            tint = MaterialTheme.colorScheme.onPrimaryContainer.copy(
                                alpha = 0.4f
                            )
                        )
                    }
                }

            }
        )
    }
}

@Preview (showBackground = true)
@Composable
fun PasswordTextFieldPreview() {
    var isPasswordVisible by remember {
        mutableStateOf(false)
    }

    PasswordTextField(
        state = rememberTextFieldState(""),
        isPasswordVisible = isPasswordVisible,
        onTogglePasswordVisibility = {
            isPasswordVisible = !isPasswordVisible
        },
        hint = "user@mail.com",
        title = "Email",
//        modifier = TODO()
    )
}