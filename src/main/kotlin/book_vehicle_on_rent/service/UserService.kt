package book_vehicle_on_rent.service

import book_vehicle_on_rent.model.user.User
import book_vehicle_on_rent.repository.UserRepository

class UserService {
    private var userRepository: UserRepository = UserRepository()

    fun addUsers(user: User) {
        userRepository.addUsers(user)
    }

    fun removeUser(user: User) {
        userRepository.removeUser(user)
    }

}