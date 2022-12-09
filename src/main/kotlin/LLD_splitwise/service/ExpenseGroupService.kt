package LLD_splitwise.service

import LLD_splitwise.model.expense.Expense
import LLD_splitwise.model.expense.ExpenseGroup
import LLD_splitwise.model.user.User
import LLD_splitwise.repository.ExpenseGroupRepository

class ExpenseGroupService {
    private val expenseGroupRepository = ExpenseGroupRepository()

    fun createExpenseGroup(groupName: String, userList: List<User>): Boolean {
        return expenseGroupRepository.createExpenseGroup(groupName = groupName, userList = userList)
    }

    fun removeExpenseGroup(groupId: String) {
        expenseGroupRepository.removeExpenseGroup(groupId)
    }

    fun addUserToExpenseGroup(groupId: String, user: User) {
        expenseGroupRepository.addUserToExpenseGroup(groupId, user)
    }

    fun removeUserFromExpenseGroup(groupId: String, user: User) {
        expenseGroupRepository.removeUserFromExpenseGroup(groupId, user)
    }

    fun updateExpenseGroup(group: ExpenseGroup) {
        expenseGroupRepository.updateExpenseGroup(group)
    }

    fun getExpenseGroupList(): List<ExpenseGroup> {
        return expenseGroupRepository.getExpenseGroupList()
    }

    fun getExpenseGroupById(groupId: String): ExpenseGroup? {
        return expenseGroupRepository.getExpenseGroupById(groupId)
    }

    fun addExpenseToGroup(groupId: String, expense: Expense): Boolean {
        return expenseGroupRepository.addExpenseToGroup(groupId, expense)
    }

    fun getBalance(userId: String, groupName: String): List<String> {
        return expenseGroupRepository.getBalance(userId, groupName)
    }

    fun getBalances(groupName: String): List<String> {
        return expenseGroupRepository.getBalances(groupName)
    }

}