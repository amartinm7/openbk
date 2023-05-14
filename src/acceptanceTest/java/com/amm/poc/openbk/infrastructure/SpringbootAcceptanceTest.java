package com.amm.poc.openbk.infrastructure;

import com.github.dockerjava.api.command.CreateContainerCmd;
import com.github.dockerjava.api.model.ExposedPort;
import com.github.dockerjava.api.model.HostConfig;
import com.github.dockerjava.api.model.PortBinding;
import com.github.dockerjava.api.model.Ports;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.function.Consumer;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpringbootAcceptanceTest {

    @Value("${spring.datasource.dbname}")
    private static String dbName;

    @Value("${spring.datasource.username}")
    private static String dbUserName;

    @Value("${spring.datasource.password}")
    private static String dbPass;

    private static final int CONTAINER_PORT = 5432;
    private static final int LOCAL_PORT = 5432;

    private static final Consumer<CreateContainerCmd> portBinding = cmd -> cmd.withHostConfig(
            new HostConfig().withPortBindings(
                    new PortBinding(Ports.Binding.bindPort(LOCAL_PORT), new ExposedPort(CONTAINER_PORT))
            )
    );
    @Container
    protected static GenericContainer postgresqlContainer =
            new PostgreSQLContainer("postgres:14.5")
                    .withDatabaseName("myopenbk")
                    .withUsername("userdb")
                    .withPassword("passdb")
                    .withCreateContainerCmdModifier(portBinding)
                    .waitingFor(Wait.forLogMessage(".*Ready to accept connections.*\\n", 1));
}
