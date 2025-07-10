package LLD_Stack_Over_Flow.base

interface Votable {
    //String-userId, Int-vote count
    val votes: MutableMap<String, Int>

    fun addVote(userId: String, voteValue: Int) {
        if (voteValue == 1 || voteValue == -1) {
            votes.putIfAbsent(userId, voteValue)
        }
    }

    fun removeVote(userId: String, voteValue: Int) {
        if (voteValue == 1 || voteValue == -1) {
            votes.remove(userId)
        }
    }

    fun getVoteCount(): Int = votes.values.count()
}