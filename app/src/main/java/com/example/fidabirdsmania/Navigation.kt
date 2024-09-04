// Navigation.kt
package com.example.fidabirdsmania

import UserRepository
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun Navigation(userRepository: UserRepository, modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "front_screen", modifier = modifier) {
        composable("front_screen") { FrontScreen(navController) }
        composable("login_screen") { LoginScreen(navController, userRepository) }
        composable("signup_screen") { SignupScreen(navController, userRepository) }
        composable("home_screen") { HomeScreen(navController) }
        composable("wishlist_screen") { WishlistScreen() }
        composable("cart_screen") { CartScreen(navController) }
    }
}
