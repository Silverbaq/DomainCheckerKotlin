import checker.DomainLookup
import data.database.DatabaseController
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Thread.sleep

val domainLookup = DomainLookup()
val dbController = DatabaseController()

fun main(args: Array<String>) {
    //domainLookup.startChecking()
    GlobalScope.launch {
        dbController.initDB()
        dbController.insert()
        println(dbController.fetch())
    }

    sleep(3000)
}

