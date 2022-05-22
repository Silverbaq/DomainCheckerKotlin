package data.database.model

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.javatime.CurrentDateTime
import org.jetbrains.exposed.sql.javatime.datetime
import java.time.LocalDateTime

object Domains: IntIdTable() {
    val name = varchar("name", 50)
    val updatedAt = datetime("date_created").defaultExpression(CurrentDateTime)
    val available = bool("available")
}

data class Domain(
    val name: String,
    val updatedAt: LocalDateTime,
    val available: Boolean
)