package com.kotlinpl.english_learning.navigation

import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Using sealed interfaces for Screens of packages or modules
 * reduces errors due mistyping
 */
interface Screen {
    val icon: ImageVector?
        get() = null
    val route: String
}

/**
 * Auth Screens
 */
sealed interface AuthScreens{
    /**
     * Root Screens works like an entry point to the Graph of the `auth` package
     */
    data object Root : AuthScreens, Screen {
        override val route: String
            get() = "auth"
    }

    data object Intro : AuthScreens, Screen {
        override val route: String
            get() = "intro"
    }

    data object Login : AuthScreens, Screen {
        override val route: String
            get() = "login"
    }

    data object Register: AuthScreens, Screen {
        override val route: String
            get() = "register"
    }
}