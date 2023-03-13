package LLD_ATM_Machine.entities.atm_operation

class ChangePin {

    fun changePin(oldPin: String, newPin: String): Boolean {
        return (oldPin != newPin)
    }
}