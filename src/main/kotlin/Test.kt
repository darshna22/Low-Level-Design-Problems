/*
* FR:
* contacts of size-more than 1000-1
* my whatsapp database have millions of users present on server
* find out the users from my contacts which are on whatsapp
* */


fun main() {
    val s = "hello good morning"
    println("original string is $s")
    println("rev string is ${s.sentenceRev()}")
}

fun String?.sentenceRev(): String {
    var revString = ""
    if (this.isNullOrBlank()) {
        return "String is null or empty"
    } else {
        val array = this.split(" ")
        var right = array.size - 1
        while (right >= 0) {
            revString = revString + " " + array[right]
            right--
        }
    }

    return revString
}