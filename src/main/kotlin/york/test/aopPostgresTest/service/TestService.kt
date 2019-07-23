package york.test.aopPostgresTest.service

import org.springframework.stereotype.Service

@Service
class TestService {

    fun division(numberA: Int, numberB: Int): Int {
        return (numberA.div(numberB))
    }
}