package LLD_ATM_Machine.entities.account

import LLD_ATM_Machine.entities.ATMCard

abstract class Account(
    open val accountNumber: Long,
    open val accountBal: Double,
    open var atmCardList: List<ATMCard>,
    open var availableAccountBal: Double
)
