// LoginScreen.kt
package com.example.fidabirdsmania

import UserRepository
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.fidabirdsmania.R
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(navController: NavHostController, userRepository: UserRepository) {
    val context = LocalContext.current
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()

    Column {
        Spacer(modifier = Modifier.height(150.dp))
        Text(
            text = "Login Here",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.primary,
        )
        Spacer(modifier = Modifier.height(30.dp))
        Image(
            painter = painterResource(id = R.drawable.fbmlogo), contentDescription = null,
            modifier = Modifier.align(Alignment.CenterHorizontally),
        )
        Spacer(modifier = Modifier.height(50.dp))
        OutlinedTextField(
            value = email.value, onValueChange = { email.value = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            placeholder = { Text(text = "Email") }
        )
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            value = password.value, onValueChange = { password.value = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            placeholder = { Text(text = "Password") }
        )

        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = {
            coroutineScope.launch {
                val user = userRepository.getUserByEmailAndPassword(email.value, password.value)
                if (user != null) {
                    Toast.makeText(context, "Login Successful", Toast.LENGTH_SHORT).show()
                    navController.navigate("home_screen")
                } else {
                    Toast.makeText(context, "Invalid email or password", Toast.LENGTH_SHORT).show()
                }
            }
        },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)) {
            Text(
                text = "Login",
                modifier = Modifier,
                fontSize = 20.sp,
            )
        }

        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = {
            navController.navigate("signup_screen")
        },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)) {
            Text(
                text = "Signup",
                modifier = Modifier,
                fontSize = 20.sp,
            )
        }
    }
}
