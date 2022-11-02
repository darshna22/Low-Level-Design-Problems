package book_vehicle_on_rent.repository

import book_vehicle_on_rent.model.user.User

class UserRepository {
    private var userList = mutableListOf<User>()

    fun addUsers(user: User) {
        userList.add(user)
    }

    fun removeUser(user: User) {
        userList.remove(user)
    }
}