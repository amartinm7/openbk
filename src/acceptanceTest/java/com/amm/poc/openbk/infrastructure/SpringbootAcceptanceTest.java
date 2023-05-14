package com.amm.poc.openbk.infrastructure;

import com.amm.poc.openbk.TaskFixtures;
import com.amm.poc.openbk.infrastructure.task.repository.JpaTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

import static java.util.Map.entry;

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
    protected NamedParameterJdbcTemplate jdbcTemplate;

    static {
        MyDockerContainer dockerContainer = new MyDockerContainer();
        dockerContainer.start();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> dockerContainer.stop()));
    }

    protected void insertTask() {
        JpaTask request = TaskFixtures.ANY_JPA_TASK;
        Map<String, Object> mapper = Map.ofEntries(
                entry("uuid", request.getUuid()),
                entry("name", request.getName()),
                entry("description", request.getDescription()),
                entry("priority", request.getPriority())
        );
        jdbcTemplate.update(
                "INSERT INTO TASKS (uuid, name, description, priority, created_at, modified_at) " +
                        "VALUES (:uuid::uuid, :name, :description, :priority, now(), now())",
                mapper
        );
    }

    protected void deleteTask() {
        JpaTask request = TaskFixtures.ANY_JPA_TASK;
        Map<String, Object> mapper = Map.ofEntries(
                entry("uuid", request.getUuid())
        );
        jdbcTemplate.update(
                "DELETE FROM TASKS WHERE uuid = :uuid::uuid",
                mapper
        );
    }

    protected HttpEntity getDefaultHttpEntity () {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        requestHeaders.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        return new HttpEntity<>(requestHeaders);
    }

    protected <T> HttpEntity<T> getHttpEntity (T request) {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        requestHeaders.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        return new HttpEntity<T>(request, requestHeaders);
    }
}
