package LLD_splitwise.model.user

import java.util.UUID

data class User(
    val userId: String,
    val userName: String,
    val userEmail: String,
    val userMobile: String
)