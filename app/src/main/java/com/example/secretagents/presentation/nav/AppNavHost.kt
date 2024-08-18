package com.example.secretagents.presentation.nav

import androidx.compose.runtime.Composable

import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.secretagents.presentation.ui.DetailsScreen
import com.example.secretagents.presentation.ui.MainScreen

@Composable
fun AppNavHost(navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = "main_screen") {
        composable("main_screen") { MainScreen(navController) }
        composable(
            route = "user_details/{name}/{email}/{picture}/{phone}/{dob}/{age}/{gender}/{location}/{coordinates}/{nat}",
            arguments = listOf(
                navArgument("name") { type = NavType.StringType },
                navArgument("email") { type = NavType.StringType },
                navArgument("picture") { type = NavType.StringType },
                navArgument("phone") { type = NavType.StringType },
                navArgument("dob") { type = NavType.StringType },
                navArgument("age") { type = NavType.StringType },
                navArgument("gender") { type = NavType.StringType },
                navArgument("location") { type = NavType.StringType },
                navArgument("coordinates") { type = NavType.StringType },
                navArgument("nat") { type = NavType.StringType },
            )
        ) { backStackEntry ->
            val name = backStackEntry.arguments?.getString("name")
            val email = backStackEntry.arguments?.getString("email")
            val picture = backStackEntry.arguments?.getString("picture")
            val phone = backStackEntry.arguments?.getString("phone")
            val dob = backStackEntry.arguments?.getString("dob")
            val age = backStackEntry.arguments?.getString("age")
            val gender = backStackEntry.arguments?.getString("gender")
            val location = backStackEntry.arguments?.getString("location")
            val coordinates = backStackEntry.arguments?.getString("coordinates")
            val nat = backStackEntry.arguments?.getString("nat")
            DetailsScreen(
                name,
                email,
                picture,
                location,
                dob,
                phone,
                age.toString(),
                gender,
                coordinates,
                nat
            )
        }
    }
}