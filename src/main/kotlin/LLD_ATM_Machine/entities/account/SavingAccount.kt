package LLD_ATM_Machine.entities.account

import LLD_ATM_Machine.entities.ATMCard

data class SavingAccount(
    override val accountNumber: Long,
    override var accountBal: Double,
    override var atmCardList: List<ATMCard>,
    override var availableAccountBal: Double

) : Account(accountNumber, accountBal, atmCardList, availableAccountBal)
