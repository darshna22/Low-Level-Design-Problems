package LLD_splitwise.model.split

import LLD_splitwise.model.user.User

abstract class Split(open val paidByUser: User,open val user: User, open var amount: Double = 0.0, open var isSplitPaid: Boolean = false)

