package data.database

import data.database.model.Domain
import data.database.model.Domains
import data.database.model.Domains.available
import data.database.model.Domains.name
import data.database.model.Domains.updatedAt
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import java.sql.DriverManager

class DatabaseController(
    private val url: String,
    private val driver: String,
    private val user:String,
    private val password:String
) {
    var database: Database = Database.connect(url, driver, user, password)

    suspend fun initDB() {
        transaction(database) {
            addLogger(StdOutSqlLogger)

            SchemaUtils.create(Domains)
        }

    }

    suspend fun insert(domain: String, available: Boolean) : Boolean {
        return transaction(database) {
            try {
                Domains.insert {
                    it[name] = domain
                    it[Domains.available] = available
                }
                true
            } catch (ex: Exception) {
                false
            }
        }
    }

    suspend fun fetch(domain: String): Domain {
        return transaction {
            val domain = Domains.select { name eq domain }.single()
            val name = domain[name]
            val updated = domain[updatedAt]
            val available = domain[available]
            Domain(name, updated, available)
        }
    }
}