package LLD_SwigyApp.repository

import LLD_SwigyApp.model.user.User


class UserRepository {
    private var userMap: MutableMap<String, User> = mutableMapOf<String, User>()

    fun addUser(user: User) {
        if (userMap.containsKey(user.userId)) {
            throw Exception("This user already added")
        }
        userMap[user.userId] = user

    }

    fun removeUserById(userId: String) {
        userMap.remove(userId)
    }

    fun getUserById(userId: String): User? {
        if (!userMap.containsKey(userId))
            throw Exception("User does not exist")
        return userMap[userId]
    }



    fun getAllUsers(): List<User> {
        return userMap.values as List<User>
    }

}
