package LLD_ATM_Machine.entities

import LLD_ATM_Machine.entities.account.Account
import LLD_ATM_Machine.enums.CustomerStatus
import java.util.UUID

data class Customer(
    val customerId: String = UUID.randomUUID().toString(),
    val userName: String,
    val userEmail: String,
    val userPhone: String,
    val userAddress: Address,
    val customerStatus: CustomerStatus,
    var account: List<Account> = emptyList<Account>()
)
