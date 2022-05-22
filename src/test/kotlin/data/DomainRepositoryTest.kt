package data

import data.database.DatabaseController
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

@OptIn(ExperimentalCoroutinesApi::class)
class DomainRepositoryTest {

    private val databaseController: DatabaseController = mock()

    private val repository = DomainRepository(databaseController)

    @Test
    fun getUncheckedDomain_noDomainsStored_providesADomain() {
        val actual = repository.getUncheckedDomain()
        Assertions.assertEquals(5, actual.length)
    }

    @Test
    fun getUncheckedDomain_checksDBIfDomainHasBeenCecked() = runTest {
        repository.getUncheckedDomain()
        verify(databaseController).fetch(any())
    }
}