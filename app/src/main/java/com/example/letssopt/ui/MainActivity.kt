package com.example.letssopt.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.example.letssopt.core.local.BuyingItemDatabase
import com.example.letssopt.ui.home.Main
import com.example.letssopt.ui.home.MainRoute
import com.example.letssopt.ui.home.MainViewModel
import com.example.letssopt.ui.home.MainViewModelFactory
import com.example.letssopt.ui.login.Login
import com.example.letssopt.ui.login.LoginRoute
import com.example.letssopt.ui.login.LoginViewModel
import com.example.letssopt.ui.signup.SignUp
import com.example.letssopt.ui.signup.SignUpRoute
import com.example.letssopt.ui.theme.LETSSOPTTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LETSSOPTTheme {
                val navController = rememberNavController()

                val database = BuyingItemDatabase.getDatabase(LocalContext.current)
                val dao = database.buyingItemDao()

                val mainViewModel: MainViewModel = viewModel(
                    factory = MainViewModelFactory(dao)
                )
                val loginViewModel by viewModels<LoginViewModel>()

                NavHost(
                    navController = navController,
                    startDestination = if (loginViewModel.getIsLoggedIn()) Main else Login,
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
                    composable<Login> {
                        LoginRoute(
                            navigateToMain = {
                                val navOptions = navOptions {
                                    popUpTo<Login> {
                                        inclusive = true
                                    }
                                }
                                navController.navigate(Main, navOptions)
                            },
                            navigateToSignUp = {
                                navController.navigate(SignUp)
                            }
                        )
                    }

                    composable<Main> {
                        MainRoute(
                            viewModel = mainViewModel,
                            onNavigateToProfile = {
//                                navController.navigate()
                            }
                        )
                    }

                    composable<SignUp> {
                        SignUpRoute() {
                            navController.popBackStack()
                        }
                    }

                }
            }
        }
    }
}
