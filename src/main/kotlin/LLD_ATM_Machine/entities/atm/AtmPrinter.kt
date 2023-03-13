package LLD_ATM_Machine.entities.atm

import LLD_ATM_Machine.entities.Receipt

class AtmPrinter(val atmDisplay: ATMDisplay) {
    fun printReceipt(receipt: Receipt) {
        atmDisplay.displayMsg(receipt.toString())
    }
}