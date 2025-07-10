package LLD_Stack_Over_Flow.service

import LLD_Stack_Over_Flow.model.Comment
import LLD_Stack_Over_Flow.model.Question
import LLD_Stack_Over_Flow.model.user.User
import java.util.UUID

class QuestionService {

    val questions = mutableMapOf<String, MutableList<Question>>()//mapOf(userId,question)

    @Synchronized
    fun postQuestion(questionBody: String, user: User) {
        val userQuestions = questions.getOrPut(user.id) { mutableListOf() }
        userQuestions.add(
            Question(
                UUID.randomUUID().toString(),
                user,
                questionBody,
                System.currentTimeMillis()
            )
        )
    }

    @Synchronized
    fun voteQuestion(questionId: String, userId: String, voteValue: Int) {
        val userQuestions = questions[userId] ?: return
        val question = userQuestions.find { it.id == questionId } ?: return
        question.addVote(userId, voteValue)
    }


    fun getQuestionsPostByUser(userId: String): List<Question> {
        return questions[userId] ?: emptyList()
    }

    @Synchronized
    fun commentOnQuestion(questionId: String, user: User, commentBody: String) {
        val userQuestions = questions[user.id] ?: return
        val question = userQuestions.find { questionId == it.id } ?: return
        question.addComment(
            Comment(
                id = UUID.randomUUID().toString(),
                author = user,
                body = commentBody,
                timestamp = System.currentTimeMillis()
            )
        )
    }

    fun getCommentsOnQuestion(questionId: String, user: User): List<Comment> {
        val userQuestions = questions[user.id] ?: emptyList()
        val question = userQuestions.find { questionId == it.id } ?: return emptyList()
        return question.comments
    }
}