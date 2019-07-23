import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	kotlin("plugin.jpa") version "1.2.71"
	id("org.springframework.boot") version "2.1.6.RELEASE"
	id("io.spring.dependency-management") version "1.0.7.RELEASE"
	kotlin("jvm") version "1.2.71"
	kotlin("plugin.spring") version "1.2.71"
}

group = "york.test"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

val developmentOnly by configurations.creating
configurations {
	runtimeClasspath {
		extendsFrom(developmentOnly)
	}
}

repositories {
	mavenCentral()
}

dependencies {

	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	// implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-data-rest")
	compile("org.springframework.data:spring-data-rest-webmvc:3.1.9.RELEASE") // for spring-data-rest
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

	implementation("org.postgresql:postgresql:42.2.5")
	implementation("io.springfox:springfox-data-rest:2.9.2")

	// https://mvnrepository.com/artifact/com.vladmihalcea/hibernate-types-52
	compile("com.vladmihalcea:hibernate-types-52:2.4.3")
	// https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-aop
	compile ("org.springframework.boot:spring-boot-starter-aop:2.1.6.RELEASE")

	developmentOnly("org.springframework.boot:spring-boot-devtools")

	// Switching from JUnit 4 to JUnit 5
	testImplementation("org.springframework.boot:spring-boot-starter-test") {
		//		exclude(module = "junit")
	}
	testImplementation("org.junit.jupiter:junit-jupiter-api")
	//testImplementation ("com.ninja-squad:springmockk")
	//testImplementation ("io.mockk:mockk")
	testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
	// https://mvnrepository.com/artifact/org.testcontainers/postgresql
	testCompile("org.testcontainers:postgresql:1.11.3")
	// https://mvnrepository.com/artifact/junit/junit
	testCompile("junit:junit:4.12")

}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "1.8"
	}
}

// For JUnit 5
tasks.withType<Test> {
	useJUnitPlatform()
}
