package LLD_Stack_Over_Flow

import LLD_Stack_Over_Flow.model.user.User
import LLD_Stack_Over_Flow.service.AnswerService
import LLD_Stack_Over_Flow.service.QuestionService
import LLD_Stack_Over_Flow.service.ReputationService

class StackOverflowClient {
    // Initialize services
    private val questionService: QuestionService = QuestionService()
    private val answerService: AnswerService = AnswerService(questionService)

    // Instantiate StackOverFlow
    private val stackOverFlowSystem = StackOverFlow(
        questionService,
        answerService,
        ReputationService
    )

    fun demonstrateUsage() {
        // Create some users (assuming a Member class that extends User)
        val user1 = User(id = "user123", name = "Alice")
        val user2 = User(id = "user456", name = "Bob")

        println("--- Demonstrating StackOverflow System ---")

        // 1. User1 posts a question
        stackOverFlowSystem.postQuestion(user1, "What is the best way to learn Kotlin?")
        val questionsByAlice = stackOverFlowSystem.getQuestionsPostByUser(user1.id)
        val firstQuestionId = questionsByAlice.firstOrNull()?.id

        if (firstQuestionId != null) {
            // 2. User2 votes on User1's question
            stackOverFlowSystem.voteQuestion(firstQuestionId, user2.id, 1) // Upvote

            // 3. User2 comments on User1's question
            stackOverFlowSystem.commentOnQuestion(firstQuestionId, user2, "Great question! I'm also interested.")

            // 4. User1 gets comments on their question
            val commentsOnFirstQuestion = stackOverFlowSystem.getCommentsOnQuestion(firstQuestionId, user1)
            println("Comments on Alice's question:")
            commentsOnFirstQuestion.forEach { println("- ${it.author.name}: ${it.body}") }

            // 5. User2 posts an answer to User1's question
            stackOverFlowSystem.postAnswer(user2, firstQuestionId, "I recommend checking the official Kotlin documentation and trying out some small projects.")

            // (Demonstrating direct use of reputation service, though it might be called internally)
            // For example, if posting an answer gives reputation:
//            stackOverFlowSystem.reputationService.(user2.id, 10) // Bob gets 10 points for answering

        } else {
            println("Alice hasn't posted any questions yet.")
        }


        // 6. User2 posts a new question
        stackOverFlowSystem.postQuestion(user2, "How to handle asynchronous tasks in Android?")
        val questionsByBob = stackOverFlowSystem.getQuestionsPostByUser(user2.id)
        println("\nQuestions posted by Bob:")
        questionsByBob.forEach { println("- ${it.body} (ID: ${it.id})") }

        println("\n--- End of Demonstration ---")
    }
}
