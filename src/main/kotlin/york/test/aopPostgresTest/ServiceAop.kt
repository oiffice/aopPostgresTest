package york.test.aopPostgresTest

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Aspect
@Component
class ServiceAop {

    private val log = LoggerFactory.getLogger(this.javaClass)

    @Pointcut(" execution(* york.test.aopPostgresTest.service.TestService.division(..)) && args(numberA, numberB)")
    fun divisionTest(numberA: Int, numberB: Int){}

    @Around("divisionTest(numberA, numberB)")
    @Throws(Exception::class)
    fun doValidate(pj: ProceedingJoinPoint, numberA: Int, numberB: Int): Any {

        if (numberA <= 0 || numberB <=0 ) {
            throw Exception("Parameter should greater than zero")
        }

        return pj.proceed()

    }
}