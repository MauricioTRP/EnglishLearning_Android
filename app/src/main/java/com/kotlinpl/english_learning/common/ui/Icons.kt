package com.kotlinpl.english_learning.common.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.kotlinpl.english_learning.R

val EyeOpen : ImageVector
    @Composable
    get() = ImageVector.vectorResource(id = R.drawable.eye_opened)

val EyeClosed : ImageVector
    @Composable
    get() = ImageVector.vectorResource(id = R.drawable.eye_closed)