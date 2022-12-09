package LLD_splitwise

import LLD_splitwise.enums.SplitType
import LLD_splitwise.model.split.Split
import LLD_splitwise.model.user.User

object SplitWiseRunner {
    @JvmStatic
    fun main(array: Array<String>) {
        // Input Sample Users
        val user1 = User("1", "u1", "u1@gmail.com", "9890098900")
        val user2 = User("2", "u2", "u2@gmail.com", "9999999999")
        val user3 = User("3", "u3", "u3@gmail.com", "9898989899")
        val user4 = User("4", "u4", "u4@gmail.com", "8976478292")
        val userList = mutableListOf<User>(user1, user2, user3, user4)

        val expenseManager = ExpenseManager()
        //create group
        val isGroupCreated = expenseManager.createGroup(groupName = "Netflix", userList = userList)
        if (isGroupCreated)
            println("group created successfully")
        else
            println("group already available with same name")

        val balanceList = expenseManager.showBalances(groupName = "Netflix")
        if (balanceList?.isNotEmpty() == true) {
            for (user in balanceList) {
                println(user)
            }
        } else {
            println("No balances")
        }
        val userBalanceList = expenseManager.showBalance(userId = "1", groupName = "Netflix")
        if (userBalanceList.isNotEmpty()) {
            for (user in userBalanceList) {
                println(user)
            }
        } else {
            println("No balances")
        }
        //create expense to group
        val groupMember = expenseManager.getExpenseGroup(groupName = "Netflix")
        expenseManager.createExpense(
            groupName = groupMember?.groupName ?: "",
            SplitType.EQUAL,
            paidAmt = 1000.00,
            expensePaidBy = user1,
            splitWithMembers = groupMember?.listOfGroupMembers ?: emptyList(),
            expenseTitle = "Netflix payment",
            listOfExactOrPercent = emptyList()
        )
        val userBalanceListUser4 = expenseManager.showBalance(userId = "4", groupName = "Netflix")
        if (userBalanceListUser4.isNotEmpty()) {
            for (stmt in userBalanceListUser4) {
                println(stmt)
            }
        } else {
            println("No balances")
        }

        val userBalanceListUser1 = expenseManager.showBalance(userId = "1", groupName = "Netflix")
        if (userBalanceListUser1.isNotEmpty()) {
            for (stmt in userBalanceListUser1) {
                println(stmt)
            }
        } else {
            println("No balances")
        }

        val users = mutableListOf<User>()
        users.add(user2)
        users.add(user3)
        val share = mutableListOf<Double>()
        share.add(370.0)
        share.add(880.0)
        expenseManager.createExpense(
            groupName = groupMember?.groupName ?: "",
            SplitType.EXACT,
            paidAmt = 1250.00,
            expensePaidBy = user1,
            splitWithMembers = users,
            expenseTitle = "Movie",
            listOfExactOrPercent = share
        )

        val userBalanceListUser = expenseManager.showBalances(groupName = "Netflix")
        if (userBalanceListUser.isNotEmpty()) {
            for (stmt in userBalanceListUser) {
                println(stmt)
            }
        } else {
            println("No balances")
        }

        val percentUsers = mutableListOf<User>()
        percentUsers.add(user1)
        percentUsers.add(user2)
        percentUsers.add(user3)
        percentUsers.add(user4)
        val percentShare = mutableListOf<Double>()
        percentShare.add(40.0)
        percentShare.add(20.0)
        percentShare.add(20.0)
        percentShare.add(20.0)
        expenseManager.createExpense(
            groupName = groupMember?.groupName ?: "",
            SplitType.PERCENT,
            paidAmt = 1200.00,
            expensePaidBy = user4,
            splitWithMembers = percentUsers,
            expenseTitle = "Party",
            listOfExactOrPercent = percentShare
        )

        val userBalanceListUser5 = expenseManager.showBalance(userId = "4", groupName = "Netflix")
        if (userBalanceListUser5.isNotEmpty()) {
            for (stmt in userBalanceListUser5) {
                println(stmt)
            }
        } else {
            println("No balances")
        }

        val balanceList1 = expenseManager.showBalances(groupName = "Netflix")
        if (balanceList1.isNotEmpty()) {
            for (user in balanceList1) {
                println(user)
            }
        } else {
            println("No balances")
        }
    }

}