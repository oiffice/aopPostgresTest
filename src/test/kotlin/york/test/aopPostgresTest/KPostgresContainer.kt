package york.test.aopPostgresTest

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.junit.ClassRule
import org.junit.rules.ExternalResource
import org.springframework.boot.test.util.TestPropertyValues
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.EnableAspectJAutoProxy
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.containers.wait.strategy.LogMessageWaitStrategy
import java.time.Duration
import java.time.temporal.ChronoUnit

/**
 * This is reference by {@link https://robintegg.com/2019/02/09/testing-spring-boot-applications-with-testcontainers}
 */
open class KPostgresContainer(imageName: String) : PostgreSQLContainer<KPostgresContainer>(imageName) {

    val objectMapper = ObjectMapper().registerModule(KotlinModule())
    companion object {
        val postgresSQLContainer: KPostgresContainer = KPostgresContainer("postgres:latest")
                .withDatabaseName("anchor-account")
                .withUsername("york")
                .withPassword("york")
                .withExposedPorts(5432).waitingFor(LogMessageWaitStrategy()
                        .withRegEx(".*database system is ready to accept connections.*\\s")
                        .withTimes(2)
                        .withStartupTimeout(Duration.of(60, ChronoUnit.SECONDS)))

        @ClassRule
        @JvmField
        val resource: ExternalResource = object : ExternalResource() {


            override fun before() {
                println("ClassRule Before")
                this@Companion.postgresSQLContainer.start()
            }


            override fun after() {
                println("ClassRule After")
            }
        }
    }

    /**
     * Those top three annotations are for unit testing also can follow the aop rules
     */
    @Configuration
    @EnableAspectJAutoProxy
    @ComponentScan(basePackages = ["york.test.aopPostgresTest"])
    internal class Initializer : ApplicationContextInitializer<ConfigurableApplicationContext> {
        override fun initialize(configurableApplicationContext: ConfigurableApplicationContext) {

            TestPropertyValues.of(
                    "spring.datasource.url=jdbc:postgresql://192.168.99.100:5432/aop-test",
                    "spring.datasource.username=york",
                    "spring.datasource.password=york"
            ).applyTo(configurableApplicationContext.environment)
        }
    }
}