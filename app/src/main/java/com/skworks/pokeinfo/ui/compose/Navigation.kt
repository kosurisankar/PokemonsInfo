package com.skworks.pokeinfo.ui.compose

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.colorResource
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.skworks.pokeinfo.R
import com.skworks.pokeinfo.viewmodel.GetPokemonListViewModel


@Composable
fun MainNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "splash") {
        composable("splash") {
            SplashScreenWithAnimation(navController)
        }
        composable("Pokemons_List") {
            val viewModel = GetPokemonListViewModel()
            AllPokemonsList(viewModel, navController)
        }
        composable(
            "Pokemon_details/{pokemonId}/{colorCode}",
            arguments = listOf(navArgument("pokemonId") { type = NavType.IntType },
                navArgument("colorCode") { type = NavType.IntType })
        ) { backStackEntry ->
            // Extract the argument from the backStackEntry arguments
            OnPokemonNameClick(
                backStackEntry.arguments?.getInt("pokemonId") ?: 0, Color(
                    backStackEntry.arguments?.getInt("colorCode")
                        ?: colorResource(id = R.color.white).toArgb()
                )
            )
        }
        // Nested Navigation for settings
//        navigation(startDestination = "settings_home", route = "settings") {
//            composable("settings_home") {
//                SettingsHome(navController)
//            }
//            composable("settings_detail") {
//                SettingsDetailScreen(navController)
//            }
//        }
    }
}