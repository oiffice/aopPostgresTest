package york.test.aopPostgresTest

import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringRunner
import york.test.aopPostgresTest.service.TestService

@RunWith(SpringRunner::class)
@SpringBootTest
@ContextConfiguration(initializers = [KPostgresContainer.Initializer::class])
class TestServiceTestCase {

    @Autowired
    lateinit var testService: TestService

    @Test
    fun testNormalCase() {

        assertEquals(4, testService.division(8, 2))
    }

    @Test(expected = Exception::class)
    fun testErrorCase() {
        testService.division(6, 0)
    }
}