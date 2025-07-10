package LLD_Stack_Over_Flow.model

import LLD_Stack_Over_Flow.base.Commentable
import LLD_Stack_Over_Flow.base.Postable
import LLD_Stack_Over_Flow.base.Votable
import LLD_Stack_Over_Flow.model.user.User

class Question(
    override val id: String,
    override val author: User,
    override val body: String,
    override val timestamp: Long,
    override val comments: MutableList<Comment> = emptyList<Comment>().toMutableList(),
    override val votes: MutableMap<String, Int> = emptyMap<String, Int>().toMutableMap(),
    val answers: MutableList<Answer> = emptyList<Answer>().toMutableList()
) : Postable, Commentable, Votable {

    override fun addComment(comment: Comment) {
        comments.add(comment)
    }

    fun addAnswer(answer: Answer) {
        answers.add(answer)
    }
}