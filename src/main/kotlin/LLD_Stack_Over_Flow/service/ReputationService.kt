package LLD_Stack_Over_Flow.service

import LLD_Stack_Over_Flow.model.user.User

object ReputationService {

    fun onPostQuestion(user: User) {
        user.reputationScore += 5
    }

    fun onPostAnswer(user: User) {
        user.reputationScore += 10
    }

    fun onUpvotePost(postAuthor: User, isAnswer: Boolean) {
        postAuthor.reputationScore += if (isAnswer) 15 else 10
    }

    fun onDownVotePost(postAuthor: User) {
        postAuthor.reputationScore -= 2
    }

    fun onDownVoteVoter(voter: User) {
        voter.reputationScore -= 1
    }

    fun onAcceptedAnswer(user: User) {
        user.reputationScore += 25
    }
}
