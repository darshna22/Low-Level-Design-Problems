package LLD_ATM_Machine

import LLD_ATM_Machine.entities.ATMCard
import LLD_ATM_Machine.entities.account.Account
import LLD_ATM_Machine.entities.Address
import LLD_ATM_Machine.entities.Bank
import LLD_ATM_Machine.entities.Customer
import LLD_ATM_Machine.entities.account.SavingAccount
import LLD_ATM_Machine.entities.atm.ATMDisplay
import LLD_ATM_Machine.entities.atm.AtmMachine
import LLD_ATM_Machine.entities.atm.AtmPrinter
import LLD_ATM_Machine.entities.atm.CardReader
import LLD_ATM_Machine.entities.atm.keypad.ATMKeypad
import LLD_ATM_Machine.enums.AtmOperations
import LLD_ATM_Machine.enums.CustomerStatus
import LLD_ATM_Machine.util.MyUtility
import java.util.UUID

fun main() {
    //create bank add
    val customerAdd = Address(city = "agra", areaPin = "282006", state = "UP", streetAddress = "Agra")
    val bankAdd = customerAdd
    val bank = Bank(bankName = "SBI", bankAdd = bankAdd, bankCode = UUID.randomUUID().toString(), userMap = emptyMap())
    //open account for user
    val account =
        SavingAccount(123456789123456, accountBal = 20000.0, availableAccountBal = 20000.0, atmCardList = emptyList())
    val customerBankAccountsList = mutableListOf(account)
    val customer = Customer(
        userName = "darshna",
        userEmail = "darshna@gmail.com",
        userPhone = "9999999999",
        userAddress = customerAdd,
        customerStatus = CustomerStatus.ACTIVE,
        account = customerBankAccountsList
    )
    val customerBankAccountsListMap = mutableMapOf(customer.userEmail to customer)
    bank.userMap = customerBankAccountsListMap
    val atmCard =
        ATMCard(
            "123456781345678",
            cardExpiryDate = MyUtility.getCurrentDate(),
            cardHolderName = "Darshna",
            withDrawLimit = 1000000.0,
            account = account,
            atmPin = "123456"
        )

    val atmMachine = AtmMachine(
        atmDisplay = ATMDisplay(),
        atmKeypad = ATMKeypad(4),
        atmPrinter = AtmPrinter(ATMDisplay()),
        cardReader = CardReader(),
        atmDispenser = ATMDispenser()
    )
    //1. insert card
    atmMachine.insertCard(atmCard)
    //2. enter pin
    atmMachine.enterPin("123456")
    //3. selectedOption
    atmMachine.selectedOption(AtmOperations.Withdraw)
    //4. enter withdraw amt
    atmMachine.enterWithdrawAmt(2000)
    //5. check available balance
    atmMachine.checkAvailableBalance()

}