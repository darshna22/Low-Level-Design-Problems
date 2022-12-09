package LLD_splitwise.model.expense

import LLD_splitwise.model.user.User
import java.util.*

data class ExpenseGroup(
    val groupName: String,
    var listOfGroupMembers: MutableList<User>,
    var listOfExpenses: MutableList<Expense>? = emptyList<Expense>().toMutableList(),
    var userBalanceSheet: MutableMap<String, Map<String, Double>>? = emptyMap<String, Map<String, Double>>().toMutableMap()
)
