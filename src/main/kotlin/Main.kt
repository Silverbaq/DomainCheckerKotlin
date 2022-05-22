import checker.DomainLookup
import data.database.DatabaseController
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Thread.sleep

val domainLookup = DomainLookup()

fun main(args: Array<String>) {
    GlobalScope.launch {
        domainLookup.startChecking()
    }

    sleep(3000)
}

