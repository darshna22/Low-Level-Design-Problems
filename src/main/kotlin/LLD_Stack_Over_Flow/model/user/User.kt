package LLD_Stack_Over_Flow.model.user

import java.util.UUID

data class User(
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    var reputationScore: Int = 0
)
