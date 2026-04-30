package com.example.letssopt.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.example.letssopt.ui.home.MAIN
import com.example.letssopt.ui.home.MainScreen
import com.example.letssopt.ui.login.LOGIN
import com.example.letssopt.ui.login.LoginScreen
import com.example.letssopt.ui.login.LoginViewModel
import com.example.letssopt.ui.signup.SIGNUP
import com.example.letssopt.ui.signup.SignupScreen
import com.example.letssopt.ui.theme.LETSSOPTTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LETSSOPTTheme {
                val navController = rememberNavController()
                val viewModel by viewModels<LoginViewModel>()

                NavHost(
                    navController = navController,
                    startDestination = if (viewModel.getIsLoggedIn()) MAIN else LOGIN,
                    enterTransition = {
                        slideInHorizontally(
                            initialOffsetX = { fullWidth -> fullWidth },
                            animationSpec = tween(durationMillis = 300)
                        )
                    },
                    exitTransition = {
                        slideOutHorizontally(
                            targetOffsetX = { fullWidth -> -fullWidth },
                            animationSpec = tween(durationMillis = 300)
                        )
                    },
                    popEnterTransition = {
                        slideInHorizontally(
                            initialOffsetX = { fullWidth -> -fullWidth },
                            animationSpec = tween(durationMillis = 300)
                        )
                    },
                    popExitTransition = {
                        slideOutHorizontally(
                            targetOffsetX = { fullWidth -> fullWidth },
                            animationSpec = tween(durationMillis = 300)
                        )
                    },
                ) {
                    composable<LOGIN> {
                        LoginScreen(
                            navigateToMain = {
                                val navOptions = navOptions {
                                    popUpTo<LOGIN> {
                                        inclusive = true
                                    }
                                }
                                navController.navigate(MAIN, navOptions)
                            },
                            navigateToSignUp = {
                                navController.navigate(SIGNUP)
                            }
                        )
                    }

                    composable<MAIN> {
                        MainScreen()
                    }

                    composable<SIGNUP> {
                        SignupScreen(
                            onSignUpComplete = {
                                navController.popBackStack()
                            }
                        )
                    }

                }
            }
        }
    }
}
