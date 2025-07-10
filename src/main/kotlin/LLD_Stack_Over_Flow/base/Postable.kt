package LLD_Stack_Over_Flow.base

import LLD_Stack_Over_Flow.model.user.User

interface Postable {
    val id:String
    val author:User
    val body:String
    val timestamp:Long
}