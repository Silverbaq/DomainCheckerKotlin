package data.database

import data.database.model.Domains
import data.database.model.Domains.name
import data.database.model.Domains.updatedAt
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

class DatabaseController {
    private lateinit var database: Database

    suspend fun initDB() {
        database = Database.connect("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", driver = "org.h2.Driver", user = "root", password = "")

        transaction {
            addLogger(StdOutSqlLogger)

            SchemaUtils.create(Domains)
        }

    }

    suspend fun insert() {
        transaction {
            Domains.insert {
                it[name] = "something"
            }
        }
    }

    suspend fun fetch(): Pair<String, String> {
        return transaction {
            val domain = Domains.selectAll().first()
            val name = domain[name]
            val updated = domain[updatedAt]
            name to updated.toString()
        }
    }
}