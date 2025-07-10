package LLD_Stack_Over_Flow

import LLD_Stack_Over_Flow.model.Comment
import LLD_Stack_Over_Flow.model.Question
import LLD_Stack_Over_Flow.model.user.User
import LLD_Stack_Over_Flow.service.AnswerService
import LLD_Stack_Over_Flow.service.QuestionService
import LLD_Stack_Over_Flow.service.ReputationService

class StackOverFlow(
    private val questionService: QuestionService,
    private val answerService: AnswerService,
    val reputationService: ReputationService
) {
    fun postQuestion(user: User, questionBody: String) {
        questionService.postQuestion(questionBody, user)
    }

    fun voteQuestion(questionId: String, userId: String, voteValue: Int) {
        questionService.voteQuestion(questionId, userId, voteValue)
    }

    fun getQuestionsPostByUser(userId: String): List<Question> {
        return questionService.getQuestionsPostByUser(userId)
    }

    fun commentOnQuestion(questionId: String, user: User, commentBody: String) {
        questionService.commentOnQuestion(questionId, user, commentBody)
    }

    fun getCommentsOnQuestion(questionId: String, user: User): List<Comment> {
        return questionService.getCommentsOnQuestion(questionId, user)
    }

    fun postAnswer(user: User, questionId: String, answerBody: String) {
        answerService.postAnswer(user, questionId, answerBody)
    }
}