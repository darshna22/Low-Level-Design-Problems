package LLD_ATM_Machine.entities.atm.keypad

class ATMKeypad(val size: Int) {
    var atmkeypadBoard: Array<Array<ATMButton?>> = Array(size) { arrayOfNulls(size) }

    init {
        createKeypad()
    }

    private fun createKeypad() {
        for (row in 0 until size) {
            var i = 1
            when (row) {
                1 -> {
                    i = 4
                }

                2 -> {
                    i = 6
                }

                3 -> {
                    i = 0
                }
            }
            for (col in 0 until size) {

                if (row == 0 && col == 3) {
                    atmkeypadBoard[row][col] = ATMButton(btnValue = "Clear")
                } else if (row == 1 && col == 3) {
                    atmkeypadBoard[row][col] = ATMButton(btnValue = "Cancel")
                } else if (row == 2 && col == 3) {
                    atmkeypadBoard[row][col] = ATMButton(btnValue = "Ok")
                } else if (row == 3) {
                    atmkeypadBoard[row][1] = ATMButton(btnValue = "0")
                    break
                } else {
                    atmkeypadBoard[row][col] = ATMButton(btnValue = i.toString())
                }
                i++
            }
        }
    }


    fun displayAtmKeypad() {
        for (row in 0 until size) {
            for (col in 0 until size) {
                if (atmkeypadBoard[row][col] != null) {
                    print(atmkeypadBoard[row][col]?.btnValue + "")
                } else {
                    print(" ")
                }
                print(" | ");
            }
            println()
        }
    }

    private fun getInput(row: Int, col: Int): String? {
        return atmkeypadBoard[row][col]?.btnValue
    }

    fun getUserInput(cardPin: String): String {
        return cardPin
    }



}