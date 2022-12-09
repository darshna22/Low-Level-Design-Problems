package LLD_splitwise.model.split

import LLD_splitwise.model.user.User

class PercentSplit(override val paidByUser: User, override val user: User, override var amount: Double, override var isSplitPaid:Boolean) : Split(paidByUser,user, amount)