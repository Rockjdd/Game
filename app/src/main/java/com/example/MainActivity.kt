package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.audio.HapticManager
import com.example.audio.SoundManager
import com.example.data.local.GameDatabase
import com.example.data.repository.GameRepository
import com.example.ui.screens.DailyChallengeScreen
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.MathCrossScreen
import com.example.ui.screens.PuzzleScreen
import com.example.ui.theme.MyApplicationTheme
import com.example.ui.viewmodel.DailyChallengeViewModel
import com.example.ui.viewmodel.HomeViewModel
import com.example.ui.viewmodel.MathCrossViewModel
import com.example.ui.viewmodel.PuzzleViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val database = GameDatabase.getDatabase(applicationContext)
        val repository = GameRepository(database.progressDao())
        val soundManager = SoundManager()
        val hapticManager = HapticManager(applicationContext)

        setContent {
            MyApplicationTheme {
                val navController = rememberNavController()

                val homeViewModel = remember {
                    HomeViewModel(repository)
                }

                NavHost(
                    navController = navController,
                    startDestination = "home",
                    modifier = Modifier.fillMaxSize()
                ) {
                    composable("home") {
                        HomeScreen(
                            viewModel = homeViewModel,
                            onNavigateToLevel = { levelId ->
                                navController.navigate("puzzle/$levelId")
                            },
                            onNavigateToDaily = {
                                navController.navigate("daily")
                            },
                            onNavigateToMathCross = {
                                navController.navigate("math_cross")
                            }
                        )
                    }

                    composable(
                        route = "puzzle/{levelId}",
                        arguments = listOf(navArgument("levelId") { type = NavType.IntType })
                    ) { backStackEntry ->
                        val levelId = backStackEntry.arguments?.getInt("levelId") ?: 1
                        val puzzleViewModel = remember(levelId) {
                            PuzzleViewModel(
                                initialLevelId = levelId,
                                repository = repository,
                                soundManager = soundManager,
                                hapticManager = hapticManager
                            )
                        }

                        PuzzleScreen(
                            viewModel = puzzleViewModel,
                            onNavigateBack = { navController.popBackStack() }
                        )
                    }

                    composable("daily") {
                        val dailyViewModel = remember {
                            DailyChallengeViewModel(
                                repository = repository,
                                soundManager = soundManager,
                                hapticManager = hapticManager
                            )
                        }

                        DailyChallengeScreen(
                            viewModel = dailyViewModel,
                            onNavigateBack = { navController.popBackStack() }
                        )
                    }

                    composable("math_cross") {
                        val crossViewModel = remember {
                            MathCrossViewModel(
                                repository = repository,
                                soundManager = soundManager,
                                hapticManager = hapticManager
                            )
                        }

                        MathCrossScreen(
                            viewModel = crossViewModel,
                            onNavigateBack = { navController.popBackStack() }
                        )
                    }
                }
            }
        }
    }
}
