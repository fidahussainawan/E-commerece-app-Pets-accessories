// MainActivity.kt
package com.example.fidabirdsmania

import UserRepository
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.fidabirdsmania.ui.theme.FidaBirdsManiaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val userRepository = UserRepository(UserDatabase.getDatabase(applicationContext).userDao())

        setContent {
            FidaBirdsManiaTheme {
                Navigation(userRepository)
            }
        }
    }
}
