package LLD_splitwise.model.expense

import LLD_splitwise.model.user.User
import LLD_splitwise.model.split.Split
import java.util.*

data class Expense(
    val expenseId: String = UUID.randomUUID().toString(),
    var expenseTitle: String,
    var amt: Double,
    var expensePaidBy: User,
    var createdAt: Long = 0.0.toLong(),
    var updatedAt: Long = 0.0.toLong(),
    var splits: List<Split>
)
