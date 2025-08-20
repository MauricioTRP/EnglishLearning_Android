package com.kotlinpl.english_learning.navigation

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.kotlinpl.english_learning.auth.presentation.login_screen.LoginScreen
import com.kotlinpl.english_learning.auth.presentation.register_screen.RegisterScreen

@Composable
fun NavigationComposable(
    navController: NavHostController, // NavController is created in MainActivity
    isLoggedIn: Boolean, // Check if the user have started a session
    modifier: Modifier = Modifier // Modifier to be used inside Scaffold of MainActivity
) {
    NavHost(
        navController = navController,
        startDestination = AuthScreens.Root.route
    ) {
        authGraph(navController, modifier)
    }
}

/**
 * `NavGraphBuilder.authGraph` works like a navController only to `auth` module
 *
 * @param navController - is passed the same navController that MainActivity uses
 */
private fun NavGraphBuilder.authGraph(navController: NavController, modifier: Modifier) {
    navigation(
        startDestination = AuthScreens.Intro.route,
        route = AuthScreens.Root.route
    ) {
        /**
         * AuthScreen Intro Composable
         */
        composable(route = AuthScreens.Intro.route) {
            Column(
                modifier = modifier
            ) {
                Text("Intro Route of Auth Package/Module")
                Button(
                    onClick = {
                        navController.navigate(AuthScreens.Login.route)
                    }
                ) {
                    Text("Go to Login")
                }
            }
        }

        /**
         * AuthScreen Login Composable
         */
        composable(route = AuthScreens.Login.route) {
            LoginScreen(
                viewModel = hiltViewModel(),
                onRegisterClick = { // Navigates to Register Composable
                    navController.navigate(AuthScreens.Register.route)
                },
                onLoggedIn = {
                    Log.d("NavGraph", "Successfully logged in")
                },
                modifier = modifier,
            )
        }

        /**
         * Register Composable
         */
        composable(route = AuthScreens.Register.route) {

            RegisterScreen(
                viewModel = hiltViewModel(),
                onLoginClick = {
                    navController.navigate(AuthScreens.Login.route)
                },
                onLoggedIn = {}, // used to navigate main screen
                modifier = modifier
            )
        }

    }
}