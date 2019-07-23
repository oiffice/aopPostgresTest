package york.test.aopPostgresTest

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@SpringBootApplication
@ComponentScan
@Configuration
class AopPostgresTestApplication

fun main(args: Array<String>) {
	runApplication<AopPostgresTestApplication>(*args)
}
