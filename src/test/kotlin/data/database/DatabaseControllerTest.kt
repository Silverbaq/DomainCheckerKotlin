package data.database

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.jupiter.api.Test


@OptIn(ExperimentalCoroutinesApi::class)
class DatabaseControllerTest {
    private val url = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1"
    private val driver = "org.h2.Driver"
    private val user = "root"
    private val password = ""

    private val databaseController = DatabaseController(url, driver, user, password)

    @Before
    fun setup() = runTest {
        databaseController.initDB()
    }

    @Test
    fun insertDomain_connectedToDatabase_domainIsStored() = runTest {
        val domain = "w4.dk"
        databaseController.initDB()
        val actual = databaseController.insert(domain)
        Assert.assertEquals(true, actual)
    }

    @Test
    fun fetchDomain_domainIsStored_returnDomain() = runTest {
        val domain = "w4.dk"
        databaseController.initDB()
        databaseController.insert(domain)
        val actual = databaseController.fetch(domain)
        Assert.assertEquals(domain, actual.first)
    }
}