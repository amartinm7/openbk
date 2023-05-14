package com.amm.poc.openbk.infrastructure;

import com.github.dockerjava.api.command.CreateContainerCmd;
import com.github.dockerjava.api.model.ExposedPort;
import com.github.dockerjava.api.model.HostConfig;
import com.github.dockerjava.api.model.PortBinding;
import com.github.dockerjava.api.model.Ports;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.client.RestTemplate;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.wait.strategy.Wait;

import java.util.List;
import java.util.function.Consumer;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpringbootAcceptanceTest {

    @Value("${spring.datasource.dbname}")
    private String dbName;

    @Value("${spring.datasource.username}")
    private String dbUserName;

    @Value("${spring.datasource.password}")
    private String dbPass;

    @LocalServerPort
    protected int port;

    @Autowired
    protected RestTemplate restTemplate;

    @Autowired
    protected JdbcTemplate jdbcTemplate;

    static {
        MyDockerContainer dockerContainer = new MyDockerContainer();
        dockerContainer.start();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> dockerContainer.stop()));
    }

    private final String FIND_BY_ID = "SELECT * FROM TASKS WHERE 1 = 1";

    protected void executeQuery() {
        List list = jdbcTemplate.queryForList(FIND_BY_ID);
    }

}
