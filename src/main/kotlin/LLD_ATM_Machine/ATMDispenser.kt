package LLD_ATM_Machine

import kotlin.math.ceil

class ATMDispenser {
    private var twoThousandNoteCount = 20
    private var fiveHundredNoteCount = 40
    private var twoHundredNoteCount = 70
    private var hundredNoteCount = 100
    private var atmTotalAmt =
        twoThousandNoteCount * 2000 + fiveHundredNoteCount * 500 + twoHundredNoteCount * 200 + hundredNoteCount * 100

    private val notes: HashMap<String, Int> = HashMap<String, Int>()

    fun withdrawMoney(withDrawMoney: Int):Boolean {
        getDominationAmt(withDrawMoney)
        return true
    }

    private fun getDominationAmt(withDrawalAmount: Int) {
        notes.clear()
        if (withDrawalAmount.toString().isEmpty())
            throw Exception("Enter Amt should be multiple of 100")
        if (withDrawalAmount > atmTotalAmt)
            throw Exception("ATM has Insufficient Amt")
        if (withDrawalAmount % 100 != 0)
            throw Exception("Enter Amt should be multiple of 100")
        else {
            dominationCalculation(withDrawalAmount)
            dispenseMoney(dispenseMoney = withDrawalAmount)
        }
    }

    private fun dispenseMoney(dispenseMoney: Int) {
        for ((key, value) in notes.entries) {
            println("Withdrawal $value notes of $key")
        }
        atmTotalAmt -= dispenseMoney
//        println("Total Withdraw money is : $dispenseMoney")
//        println("Remaining Amt in A/C is : $atmTotalAmt")

        notes.clear()
    }

    private fun dominationCalculation(mAmount: Int) {
        var amount = mAmount
        var x = 0
        if (amount > 2000) {
            x = calculateNotes(amount, 2000)
            if (x > twoThousandNoteCount) {
                x = twoThousandNoteCount
                twoThousandNoteCount = 0
            } else {
                twoHundredNoteCount -= x
            }
            if (x != 0) {
                amount -= 2000 * x
                notes["2000"] = x
            }
        }

        if (amount > 500) {
            x = calculateNotes(amount, 500)
            if (x > fiveHundredNoteCount) {
                x = fiveHundredNoteCount
                fiveHundredNoteCount = 0
            } else {
                twoHundredNoteCount -= x
            }
            amount -= 500 * x
            notes["500"] = x


        }

        if (amount > 200) {
            x = calculateNotes(amount, 200)
            if (x > twoHundredNoteCount) {
                x = twoHundredNoteCount
                twoHundredNoteCount = 0
            } else {
                twoHundredNoteCount -= x
            }
            if (x != 0) {
                amount -= 200 * x
                notes["200"] = x
            }
        }

        if (amount >= 100) {
            x = calculateNotes(amount, 100)
            if (x > hundredNoteCount) {
                x = hundredNoteCount
                hundredNoteCount = 0
            } else {
                hundredNoteCount -= x
            }
            if (x != 0) {
                amount -= 100 * x
                notes["100"] = x
            }
        }

    }

    private fun calculateNotes(mAmount: Int, denomination: Int): Int {
        var x = ceil(mAmount.toDouble() / denomination.toDouble()).toInt()
        if (denomination > 100) {
            return if (x > 1)
                x - 1
            else x
        }

        return x
    }


}