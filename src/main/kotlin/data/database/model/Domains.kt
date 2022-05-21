package data.database.model

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.javatime.CurrentDateTime
import org.jetbrains.exposed.sql.javatime.datetime

object Domains: IntIdTable() {
    val name = varchar("name", 50)
    val updatedAt = datetime("date_created").defaultExpression(CurrentDateTime)
}