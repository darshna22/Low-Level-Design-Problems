package LLD_splitwise.service

import LLD_splitwise.enums.SplitType
import LLD_splitwise.model.split.EqualSplit
import LLD_splitwise.model.split.ExactSplit
import LLD_splitwise.model.split.PercentSplit
import LLD_splitwise.model.split.Split
import LLD_splitwise.model.user.User

class SplitWiseService {
    fun createSplit(
        paidByUser: User,
        splitType: SplitType,
        splitWithMembers: List<User>,
        amt: Double,
        listOfExactOrPercent: List<Double>
    ): List<Split> {
        val groupSize = splitWithMembers.size
        val splitList = mutableListOf<Split>()
        when (splitType) {
            SplitType.EQUAL -> {
                val splitAmt = amt / groupSize
                for (user in splitWithMembers) {
                    val split = EqualSplit(paidByUser=paidByUser,user = user, amt = splitAmt, isSplitPaid = false)
                    splitList.add(split)
                }
            }

            SplitType.EXACT -> {
                var exactTotal = 0.0
                for (exactAmt in listOfExactOrPercent)
                    exactTotal += exactAmt
                if (exactTotal != amt)
                    throw Exception("exact split should be equal to total paid by user ${paidByUser.userName}")

                for ((index, user) in splitWithMembers.withIndex()) {
                    val split = ExactSplit(paidByUser=paidByUser,user = user, exactAmount = listOfExactOrPercent[index], isSplitPaid = false)
                    splitList.add(split)
                }
            }

            SplitType.PERCENT -> {
                var percentTotalCal = 0.0
                for (percent in listOfExactOrPercent)
                    percentTotalCal += calculatePercentAmt(percent, amt)
                percentTotalCal += (amt - percentTotalCal)
                if (percentTotalCal != amt)
                    throw Exception("percent split should be equal to total paid by user ${paidByUser.userName}")

                for ((index, user) in splitWithMembers.withIndex()) {
                    val split =
                        PercentSplit(paidByUser=paidByUser,
                            user = user,
                            amount = calculatePercentAmt(amt, listOfExactOrPercent[index]),
                            isSplitPaid = false
                        )
                    splitList.add(split)
                }
            }

            else ->
                return emptyList()
        }
        return splitList
    }

    private fun calculatePercentAmt(paidAmt: Double, sharePercent: Double): Double {
        return Math.round(paidAmt * sharePercent / 100).toDouble()
    }

}