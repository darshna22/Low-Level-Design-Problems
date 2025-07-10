package LLD_Stack_Over_Flow.model

import LLD_Stack_Over_Flow.base.Commentable
import LLD_Stack_Over_Flow.base.Postable
import LLD_Stack_Over_Flow.base.Votable
import LLD_Stack_Over_Flow.model.user.User

class Answer(
    override val id: String,
    override val author: User,
    override val body: String,
    override val timestamp: Long,
    override val comments: MutableList<Comment> = emptyList<Comment>().toMutableList(),
    override val votes: MutableMap<String, Int> = emptyMap<String, Int>().toMutableMap()
) : Postable, Commentable, Votable {
    override fun addComment(comment: Comment) {
        comments.add(comment)
    }
}