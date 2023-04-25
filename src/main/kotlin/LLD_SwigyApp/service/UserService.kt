package LLD_SwigyApp.service

import LLD_SwigyApp.model.user.User
import LLD_SwigyApp.repository.UserRepository

class UserService {
    private val userRepository = UserRepository()

    fun addUser(user: User) {
        userRepository.addUser(user)
    }

    fun getUserById(userId: String): User? {
        return userRepository.getUserById(userId)
    }


    fun getAllUser(): List<User> {
        return userRepository.getAllUsers()
    }

    fun removeUserById(userId: String) {
        userRepository.removeUserById(userId)
    }
}