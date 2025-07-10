package LLD_Stack_Over_Flow.service

import LLD_Stack_Over_Flow.model.Answer
import LLD_Stack_Over_Flow.model.user.User
import java.util.UUID

class AnswerService(private val questionService: QuestionService) {

    @Synchronized
    fun postAnswer(user: User, questionId: String, answerBody: String) {
        val userQuestions = questionService.questions[user.id] ?: return
        val question = userQuestions.find { it.id == questionId } ?: return
        question.addAnswer(
            Answer(
                id = UUID.randomUUID().toString(),
                author = user,
                body = answerBody,
                timestamp = System.currentTimeMillis()
            )
        )
    }

}