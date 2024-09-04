// SignupScreen.kt

package com.example.fidabirdsmania

import UserRepository
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch

@Composable
fun SignupScreen(navController: NavHostController, userRepository: UserRepository) {
    val context = LocalContext.current
    val name = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()

    Column {
        Spacer(modifier = Modifier.height(150.dp))
        Text(
            text = "Signup Here",
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
            value = name.value, onValueChange = { name.value = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            placeholder = { Text(text = "Name") }
        )
        Spacer(modifier = Modifier.height(20.dp))
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
                val newUser = User(name = name.value, email = email.value, password = password.value)
                userRepository.insert(newUser)
                Toast.makeText(context, "Signup Successful", Toast.LENGTH_SHORT).show()
                navController.navigate("login_screen")
            }
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
