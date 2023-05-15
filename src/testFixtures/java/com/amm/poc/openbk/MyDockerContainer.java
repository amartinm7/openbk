package com.amm.poc.openbk;

import com.github.dockerjava.api.command.CreateContainerCmd;
import com.github.dockerjava.api.model.ExposedPort;
import com.github.dockerjava.api.model.HostConfig;
import com.github.dockerjava.api.model.PortBinding;
import com.github.dockerjava.api.model.Ports;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.wait.strategy.Wait;

import java.util.function.Consumer;

public class MyDockerContainer {
    private static final int CONTAINER_PORT = 5432;
    private static final int LOCAL_PORT = 5432;

    private final GenericContainer dockerContainer;

    private final Consumer<CreateContainerCmd> portBinding = cmd -> cmd.withHostConfig(
            new HostConfig().withPortBindings(
                    new PortBinding(Ports.Binding.bindPort(LOCAL_PORT), new ExposedPort(CONTAINER_PORT))
            )
    );

    public MyDockerContainer() {
        dockerContainer = new PostgreSQLContainer("postgres:14.5")
                .withDatabaseName("myopenbk")
                .withUsername("userdb")
                .withPassword("passdb")
                .withCreateContainerCmdModifier(portBinding)
                .waitingFor(Wait.forListeningPort())
                .waitingFor(Wait.forLogMessage(".*database system is ready to accept connections.*\\n", 2));
    }

    public void start() {
        this.dockerContainer.start();
    }

    public void stop(){
        this.dockerContainer.stop();
    }
}
