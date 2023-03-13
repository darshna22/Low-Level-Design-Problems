package LLD_ATM_Machine.entities

import LLD_ATM_Machine.entities.account.Account
import LLD_ATM_Machine.enums.AtmCardStatus
import java.util.*

data class ATMCard(
    val atmNumber: String,
    val cardExpiryDate: Date,
    val cardHolderName: String,
    val withDrawLimit: Double,
    val atmCardStatus: AtmCardStatus = AtmCardStatus.ACTIVE,
    val account: Account,
    var atmPin:String
)
