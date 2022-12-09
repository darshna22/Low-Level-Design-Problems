package LLD_splitwise.model.split

import LLD_splitwise.model.user.User

class ExactSplit(override val paidByUser: User, user: User, exactAmount: Double, isSplitPaid:Boolean) : Split(paidByUser,user, exactAmount,isSplitPaid)