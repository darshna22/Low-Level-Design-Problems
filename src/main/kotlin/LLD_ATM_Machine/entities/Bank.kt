package LLD_ATM_Machine.entities

import LLD_ATM_Machine.entities.account.Account

data class Bank(val bankName: String, val bankAdd: Address, val bankCode: String, var userMap: Map<String, Customer>)
