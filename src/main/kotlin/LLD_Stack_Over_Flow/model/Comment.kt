package LLD_Stack_Over_Flow.model

import LLD_Stack_Over_Flow.base.Postable
import LLD_Stack_Over_Flow.model.user.User

data class Comment(
    override val id: String,
    override val author: User,
    override val body: String,
    override val timestamp: Long
) : Postable