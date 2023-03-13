package LLD_ATM_Machine.entities

import LLD_ATM_Machine.enums.TransactionStatus
import java.util.Date

data class Receipt(
    val transactionId: String,
    val transactionDate: Date,
    val transactionStatus: TransactionStatus,
    val withDrawlAmt: Int
)
