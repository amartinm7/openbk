plugins {
    java
    id("jvm-test-suite")
    id("java-test-fixtures")
    id("org.springframework.boot") version "3.0.6"
    id("io.spring.dependency-management") version "1.1.0"
}

group = "com.amm.poc"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_19

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
    implementation("org.postgresql:postgresql:42.6.0")
    implementation("org.springframework.data:spring-data-rest-hal-explorer")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.0.3")

    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.3")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.9.3")
    testRuntimeOnly("org.junit.vintage:junit-vintage-engine:5.9.3")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.3")
    testImplementation("org.assertj:assertj-core:3.23.1")
    testImplementation("org.mockito:mockito-inline:5.2.0")
    testImplementation("org.mockito:mockito-junit-jupiter:5.3.1")
    testImplementation("org.testcontainers:testcontainers:1.18.1") {
        exclude(group = "org.slf4j", module = "slf4j-api")
    }
    testImplementation("org.testcontainers:junit-jupiter:1.18.1")
    testImplementation("org.testcontainers:postgresql:1.18.1")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

testing {
    suites {
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

        val acceptanceTest by registering(JvmTestSuite::class)
        setupDependenciesFor(acceptanceTest.name)
        tasks.named("check") {
            dependsOn(testing.suites.named(acceptanceTest.name))
        }
    }
}
