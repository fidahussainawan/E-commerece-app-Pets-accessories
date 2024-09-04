// UserViewModel.kt

package com.example.fidabirdsmania

import UserRepository
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {
    private val userRepository: UserRepository
    private val _allUsers = MutableLiveData<List<User>>()
    val allUsers: LiveData<List<User>> = _allUsers

    init {
        val userDao = UserDatabase.getDatabase(application).userDao()
        userRepository = UserRepository(userDao)

        fetchUsers()
    }

    private fun fetchUsers() {
        viewModelScope.launch(Dispatchers.IO) {
            val users = userRepository.getAllUsers()
            _allUsers.postValue(users)
        }
    }

    // Function to insert a user
    fun insertUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.insert(user)
            fetchUsers() // Update the list after insertion
        }
    }

    // Function to get a specific user
    suspend fun getUser(email: String, password: String): User? {
        return userRepository.getUserByEmailAndPassword(email, password)
    }
}
