package LLD_splitwise.model.split

import LLD_splitwise.model.user.User

class EqualSplit(paidByUser:User,user: User, amt: Double,isSplitPaid:Boolean) : Split(paidByUser,user,amt,isSplitPaid)