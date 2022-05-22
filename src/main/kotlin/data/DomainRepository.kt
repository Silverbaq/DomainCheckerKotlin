package data

import data.database.DatabaseController
import kotlinx.coroutines.*
import org.jetbrains.exposed.sql.javatime.datetime
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import kotlin.random.Random
import kotlin.time.DurationUnit

class DomainRepository(
    private val databaseController: DatabaseController,
    private val externalScope: CoroutineScope = GlobalScope,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default

) {
    private val letters = ('a'..'z').joinToString("") + "1234567890"

    fun getUncheckedDomain(): String {
        val domain = "${getRandomChar()}${getRandomChar()}.dk"

        externalScope.launch(defaultDispatcher) {
            val data = databaseController.fetch(domain)
            if (data.name == domain && needsRecheck(data.updatedAt)) {

            }
        }

        return domain
    }

    private fun getRandomChar() = letters[Random.nextInt(letters.length)]

    private fun needsRecheck(date: LocalDateTime): Boolean {
        val now = LocalDateTime.now()
        return ChronoUnit.HOURS.between(date, now) > 12
    }

    fun updateDomainAvailability(domain: String, available: Boolean) {
        TODO()
    }
}