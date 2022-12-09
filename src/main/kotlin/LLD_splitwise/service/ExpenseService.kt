package LLD_splitwise.service

import LLD_splitwise.enums.SplitType
import LLD_splitwise.model.expense.Expense
import LLD_splitwise.model.split.Split
import LLD_splitwise.model.user.User


class ExpenseService {

    fun createExpense(
        paidAmt: Double,
        expensePaidBy: User,
        splits: List<Split>,
        expenseTitle: String
    ): Expense {
        return Expense(
            expenseTitle = expenseTitle,
            amt = paidAmt,
            expensePaidBy = expensePaidBy,
            splits = splits
        )
    }
}