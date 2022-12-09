package LLD_splitwise

import LLD_splitwise.enums.SplitType
import LLD_splitwise.model.expense.Expense
import LLD_splitwise.model.expense.ExpenseGroup
import LLD_splitwise.model.user.User
import LLD_splitwise.service.ExpenseGroupService
import LLD_splitwise.service.ExpenseService
import LLD_splitwise.service.SplitWiseService


class ExpenseManager {
    private val expenseGroupService = ExpenseGroupService()
    private val expenseService = ExpenseService()
    private val splitWiseService = SplitWiseService()

    fun createGroup(groupName: String, userList: List<User>): Boolean {
        return expenseGroupService.createExpenseGroup(groupName = groupName, userList = userList)
    }

    fun createExpense(
        groupName: String,
        splitType: SplitType,
        paidAmt: Double,
        expensePaidBy: User,
        splitWithMembers: List<User>,
        expenseTitle: String,
        listOfExactOrPercent: List<Double>
    ): Boolean {
        val splitWithMembersList =
            splitWiseService.createSplit(expensePaidBy, splitType, splitWithMembers, paidAmt, listOfExactOrPercent)
        val expense = expenseService.createExpense(paidAmt, expensePaidBy, splitWithMembersList, expenseTitle)
        return expenseGroupService.addExpenseToGroup(groupId = groupName, expense = expense)
    }

    fun getExpenseGroup(groupName: String): ExpenseGroup? {
        return expenseGroupService.getExpenseGroupById(groupName)
    }

    fun showBalance(userId: String, groupName: String): List<String> {
        return expenseGroupService.getBalance(userId,groupName)
    }
    fun showBalances(groupName: String): List<String> {
        return expenseGroupService.getBalances(groupName)
    }


}