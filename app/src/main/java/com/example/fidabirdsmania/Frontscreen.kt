// FrontScreen.kt
package com.example.fidabirdsmania

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import kotlinx.coroutines.delay

@Composable
fun FrontScreen(navController: NavHostController) {
    LaunchedEffect(Unit) {
        delay(3000L)  // 3 seconds delay
        navController.navigate("home_screen")
    }

    Column {
        Spacer(modifier = Modifier.height(150.dp))
        Text(
            text = "Welcome to Fida Birds Mania",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.primary,
        )
        Spacer(modifier = Modifier.height(130.dp))
        Image(painter = painterResource(id = R.drawable.fbmlogo), contentDescription = null,
            modifier = Modifier.align(Alignment.CenterHorizontally))
    }
}
