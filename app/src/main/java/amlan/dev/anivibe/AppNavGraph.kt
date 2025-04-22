package amlan.dev.anivibe

import amlan.dev.anivibe.ui.DetailsScreen
import amlan.dev.anivibe.ui.HomeScreen
import amlan.dev.anivibe.ui.LoadingScreen
import amlan.dev.anivibe.ui.ResultsScreen
import amlan.dev.anivibe.viewmodel.RecommendationViewModel
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavGraph(){

    val navController = rememberNavController()
    val viewModel = remember { RecommendationViewModel() }

    NavHost(
        navController = navController,
        startDestination = "home"
    ){
        composable("home"){
            HomeScreen(
                onSubmitPrompt = { prompt ->
                    viewModel.setCurrentPrompt(prompt)
                    navController.navigate("loading/$prompt")
                }
            )
        }

        composable("loading/{prompt}") { backStackEntry ->
            val prompt = backStackEntry.arguments?.getString("prompt") ?: ""
            LoadingScreen(
                prompt = prompt,
                viewModel = viewModel,
                onResultReady = {
                navController.navigate("results")
            })
        }

        composable("results") {
            ResultsScreen(
                viewModel,
                onBackPressed = {
                    navController.navigateUp()
                },
                prompt = viewModel.currentPrompt.collectAsState().value,
                onAnimeClick = { anime ->
                    viewModel.selectAnime(anime)
                    navController.navigate("details")
                }
            )
        }

        composable("details"){
            DetailsScreen(
                viewModel = viewModel,
                onBackPressed = { navController.navigateUp() }
            )
        }
    }
}