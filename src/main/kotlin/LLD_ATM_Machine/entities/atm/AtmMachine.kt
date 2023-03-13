package LLD_ATM_Machine.entities.atm

import LLD_ATM_Machine.ATMDispenser
import LLD_ATM_Machine.entities.ATMCard
import LLD_ATM_Machine.entities.Customer
import LLD_ATM_Machine.entities.Receipt
import LLD_ATM_Machine.entities.account.Account
import LLD_ATM_Machine.entities.atm.keypad.ATMKeypad
import LLD_ATM_Machine.enums.AtmCardStatus
import LLD_ATM_Machine.enums.AtmOperations
import LLD_ATM_Machine.enums.TransactionStatus
import LLD_ATM_Machine.util.MyUtility
import java.util.*

class AtmMachine(
    val atmDisplay: ATMDisplay,
    val atmKeypad: ATMKeypad,
    val cardReader: CardReader,
    val atmPrinter: AtmPrinter,
    val atmDispenser: ATMDispenser
) {

    private lateinit var account: Account
    private lateinit var atmCard: ATMCard
    private val latestTransaction: Deque<Receipt> = LinkedList<Receipt>()

    init {
        atmDisplay.displayMsg("enter you card")
    }

    fun insertCard(atmCard: ATMCard) {
        cardReader.readCard(atmCard)
        if (validateCard(atmCard)) {
            this.atmCard = atmCard
            account = atmCard.account
            atmDisplay.displayMsg("enter your card pin code")
            atmKeypad.displayAtmKeypad()
        } else {
            throw Exception("card is not working")
        }
    }

    private fun validateCard(atmCard: ATMCard): Boolean {
        return (atmCard.atmCardStatus == AtmCardStatus.ACTIVE)
    }

    fun enterPin(cardPin: String) {
        atmDisplay.displayMsg("entered pin is ${atmKeypad.getUserInput(cardPin)}")
        if (validateCardPin(cardPin)) {
            showOperation()
        } else {
            throw Exception("please enter correct card pin ")
        }
    }

    private fun validateCardPin(cardPin: String): Boolean {
        return (atmCard.atmPin == cardPin)
    }

    private fun showOperation() {
        atmDisplay.displayMsg("Please select option below to proceed")
        for (operation in AtmOperations.values()) {
            println(operation)
        }
    }

    fun selectedOption(atmOperation: AtmOperations) {
        when (atmOperation) {
            AtmOperations.Withdraw -> {
                atmDisplay.displayMsg("Please enter withdraw amt in multiple of 100")
            }

            AtmOperations.ChangePin -> {
                atmDisplay.displayMsg("Please enter new and old pwd")
            }

            else -> {}
        }

    }

    fun enterWithdrawAmt(withdrawAmt: Int) {
        if (withdrawAmt <= account.availableAccountBal) {
            if (atmDispenser.withdrawMoney(withdrawAmt)) {
                atmDisplay.displayMsg("withdraw amt is $withdrawAmt ₹")
                account.availableAccountBal = account.availableAccountBal - withdrawAmt
                printReceipt(withdrawAmt)
                collectAtmCard()
            }

        } else {
            throw Exception("Insufficient amt in account")
        }
    }

    private fun printReceipt(withdrawAmt: Int) {
        val receipt = Receipt(
            transactionId = UUID.randomUUID().toString(),
            transactionDate = MyUtility.getCurrentDate(),
            transactionStatus = TransactionStatus.SUCCESS,
            withDrawlAmt = withdrawAmt
        )
        latestTransaction.addFirst(receipt)
        atmPrinter.printReceipt(
            receipt = receipt
        )
    }

    private fun addTransactionToList(receipt: Receipt) {
        if (latestTransaction.size == 5) {
            latestTransaction.pollLast()
        }
        latestTransaction.addFirst(receipt)
    }

    private fun collectAtmCard() {
        atmDisplay.displayMsg("Please collect your atm card")
    }

    fun changeAtmPin(oldPin: String, newPin: String) {
        if (oldPin == newPin) {
            throw Exception("current and new pin can't be same")
        } else {
            atmCard.atmPin = newPin
            atmDisplay.displayMsg("Pin change successfully")
        }
    }

    fun showLatestTransaction() {
        while (!latestTransaction.isEmpty()) {
            atmDisplay.displayMsg(latestTransaction.pollFirst().toString())
        }
    }

    fun checkAvailableBalance() {
        atmDisplay.displayMsg("your available balance is ${account.availableAccountBal}")
    }
}