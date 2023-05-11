plugins {
	java
	`jvm-test-suite`
	id("org.springframework.boot") version "3.0.6"
	id("io.spring.dependency-management") version "1.1.0"
}

group = "com.amm.poc"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-data-rest")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.flywaydb:flyway-core")
	implementation("org.springframework.data:spring-data-rest-hal-explorer")
	implementation("org.hsqldb:hsqldb:2.7.1")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.3")
	testImplementation("org.junit.jupiter:junit-jupiter-params:5.9.3")
	testRuntimeOnly("org.junit.vintage:junit-vintage-engine:5.9.3")
	testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.3")
	testImplementation("org.assertj:assertj-core:3.23.1")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

testing {
	suites {
		val test by getting(JvmTestSuite::class) {
			useJUnitJupiter()
		}

		configureEach {
			if (this is JvmTestSuite) {
				useJUnitJupiter()
				dependencies {
					implementation(project())
					compileOnly(project())
					runtimeOnly(project())
					annotationProcessor(project())
				}
				targets {
					all {
						testTask.configure {
							shouldRunAfter(test)
						}
					}
				}
			}
		}
		fun setupDependenciesFor(moduleName: String) {
			// this is for lib dependency inheritance
			with(configurations) {
				named("${moduleName}Implementation") {
					extendsFrom(configurations["testImplementation"])
				}
				named("${moduleName}RuntimeOnly") {
					extendsFrom(configurations["testRuntimeOnly"])
				}
			}
		}

		val integrationTest by registering(JvmTestSuite::class)
		val acceptanceTest by registering(JvmTestSuite::class)

		setupDependenciesFor(integrationTest.name)
		setupDependenciesFor(acceptanceTest.name)

		tasks.named("check") {
			dependsOn(testing.suites.named(integrationTest.name))
			dependsOn(testing.suites.named(acceptanceTest.name))
		}
	}
}
