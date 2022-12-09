package LLD_splitwise.repository

import LLD_splitwise.model.expense.Expense
import LLD_splitwise.model.expense.ExpenseGroup
import LLD_splitwise.model.user.User


class ExpenseGroupRepository() {

    private var expenseExpenseGroupMap = mutableMapOf<String, ExpenseGroup>()

    fun createExpenseGroup(groupName: String, userList: List<User>): Boolean {
        if (!expenseExpenseGroupMap.containsKey(groupName)) {
            val expenseGroup = ExpenseGroup(groupName = groupName, listOfGroupMembers = userList as MutableList<User>)
            expenseExpenseGroupMap[groupName] = expenseGroup
            //create datasheet
            for (user in userList) {
                expenseGroup.userBalanceSheet?.put(user.userId, mutableMapOf<String, Double>())
            }
            return true
        }
        return false
    }


    fun removeExpenseGroup(groupId: String) {
        expenseExpenseGroupMap.remove(groupId)
    }

    fun addUserToExpenseGroup(groupId: String, user: User) {
        val expenseGroup = expenseExpenseGroupMap[groupId]
        expenseGroup?.listOfGroupMembers?.add(user)
    }

    fun removeUserFromExpenseGroup(groupId: String, user: User) {
        val expenseGroup = expenseExpenseGroupMap[groupId]
        expenseGroup?.listOfGroupMembers?.remove(user)
    }

    fun updateExpenseGroup(group: ExpenseGroup) {
        if (expenseExpenseGroupMap.containsKey(group.groupName)) {
            expenseExpenseGroupMap[group.groupName] = group
        }
    }

    fun getExpenseGroupList(): List<ExpenseGroup> {
        return expenseExpenseGroupMap.values.toList()
    }

    fun getExpenseGroupById(groupId: String): ExpenseGroup? {
        return expenseExpenseGroupMap[groupId]
    }

    fun addExpenseToGroup(groupId: String, expense: Expense): Boolean {
        if (expenseExpenseGroupMap.containsKey(groupId)) {
            val expenseGroup = expenseExpenseGroupMap[groupId]
            expenseGroup?.listOfExpenses?.add(expense)
            expenseGroup?.let { createBalanceSheetForUsers(it, expense) }
            return true
        }
        return false
    }

    private fun createBalanceSheetForUsers(expenseGroup: ExpenseGroup, expense: Expense) {
        val balanceSheet = expenseGroup.userBalanceSheet
        assert(balanceSheet != null)
        for (split in expense.splits) {
            val paidTo = split.user.userId
            var balances:MutableMap<String,Double> = balanceSheet?.get(expense.expensePaidBy.userId) as MutableMap<String, Double>
            if (!balances.containsKey(paidTo)) {
                balances[paidTo] = 0.0
            }
            balances[paidTo] = balances[paidTo]!! + split.amount
            balances = balanceSheet[paidTo] as MutableMap<String, Double>
            if (!balances.containsKey(expense.expensePaidBy.userId)) {
                balances[expense.expensePaidBy.userId] = 0.0
            }
            balances[expense.expensePaidBy.userId] = balances[expense.expensePaidBy.userId]!! - split.amount
        }
    }

    fun getBalance(userId: String, groupName: String): List<String> {
        val expenseGroup = getExpenseGroupById(groupName)
        val balanceSheet = expenseGroup?.userBalanceSheet
        val userBalanceSheet = balanceSheet!![userId]?.toMutableMap()
        val balances: MutableList<String> = ArrayList()
        if (userBalanceSheet != null) {
            for ((key, value) in userBalanceSheet) {
                if (value != 0.0) {
                    balances.add(checkSign(userId, key, value, expenseGroup))
                }
            }
        }
        return balances
    }

    fun getBalances(groupName: String): List<String> {
        val expenseGroup = getExpenseGroupById(groupName)
        val balanceSheet = expenseGroup?.userBalanceSheet
        val balances: MutableList<String> = ArrayList()
        if (balanceSheet != null) {
            for ((key, value) in balanceSheet) {
                for ((oweUser, moneyOwe) in value.entries) {
                    if (moneyOwe > 0) {
                        balances.add(checkSign(key, oweUser, moneyOwe, expenseGroup))
                    }
                }
            }
        }
        return balances
    }

    private fun checkSign(user1Id: String, user2Id: String, amount: Double, expenseGroup: ExpenseGroup): String {
        var user1Name = ""
        var user2Name = ""
        for (user in expenseGroup.listOfGroupMembers) {
            if (user.userId == user1Id) {
                user1Name = user.userName
            }
            if (user.userId == user2Id) {
                user2Name = user.userName
            }
        }
        if (amount < 0) {
            return user1Name + " owes " + user2Name + ": " + Math.abs(amount)
        } else if (amount > 0) {
            return user2Name + " owes " + user1Name + ": " + Math.abs(amount)
        }
        return ""
    }

}