package checker

import runCommand

class DomainLookup {
    private val letters = ('a'..'z').joinToString("") + "1234567890"

    fun startChecking() {
        letters.forEach { firstLetter ->
            letters.forEach { secondLetter ->
                println("[ ] Checks: $firstLetter$secondLetter.dk")
                val answer = "nslookup $firstLetter$secondLetter.dk".runCommand() ?: ""

                if (notOwned in answer) {
                    println("[+] $firstLetter$secondLetter.dk is FREE!")
                }
                Thread.sleep(200)
            }
        }
    }

    companion object {
        private const val notOwned = "** server can't find"
    }
}