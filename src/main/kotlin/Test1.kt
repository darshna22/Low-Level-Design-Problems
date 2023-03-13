/*
* [3:02 PM] Mathur, Rashmi
find the first recurring character in a given string.

[3:02 PM] Mathur, Rashmi
Input: architecture

[3:03 PM] Mathur, Rashmi
Output: c


*
* */

fun main() {
    val inputStr = "darshna"
    println("repeat character in string is ${getRepeatFirstChar(inputStr)}")
}

fun getRepeatFirstChar(inputStr: String): Char {
    val charMap = mutableMapOf<Char, Boolean>()
    val n = inputStr.length
    val repeat='v'
    var i = 0
    while (i < n) {
        if (!charMap.containsKey(inputStr[i])) {
            charMap[inputStr[i]] = true
        } else
            return inputStr[i]
        i++
    }
    return repeat
}