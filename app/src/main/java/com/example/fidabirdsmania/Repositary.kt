// UserRepository.kt

import com.example.fidabirdsmania.User
import com.example.fidabirdsmania.UserDao

class UserRepository(private val userDao: UserDao) {

    suspend fun insert(user: User) {
        userDao.insert(user)
    }

    suspend fun getUserByEmailAndPassword(email: String, password: String): User? {
        return userDao.getUser(email, password)
    }

    suspend fun getAllUsers(): List<User> {
        return userDao.getAllUsers()
    }
}
