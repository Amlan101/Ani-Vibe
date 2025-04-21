package amlan.dev.anivibe

import amlan.dev.anivibe.ui.HomeScreen
import amlan.dev.anivibe.ui.LoadingScreen
import amlan.dev.anivibe.ui.ResultsScreen
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavGraph(){
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home"
    ){
        composable("home"){
            HomeScreen(
                onSubmitPrompt = { prompt ->
                    navController.navigate("loading/$prompt")
                }
            )
        }

        composable("loading/{prompt}") { backStackEntry ->
            val prompt = backStackEntry.arguments?.getString("prompt") ?: ""
            LoadingScreen(prompt = prompt, onResultReady = {
                navController.navigate("results")
            })
        }

        composable("results") {
            ResultsScreen()
        }

    }
}