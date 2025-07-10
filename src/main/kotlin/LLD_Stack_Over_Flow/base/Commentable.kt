package LLD_Stack_Over_Flow.base

import LLD_Stack_Over_Flow.model.Comment

interface Commentable :Postable{
   val comments: MutableList<Comment>
    fun addComment(comment: Comment)
}