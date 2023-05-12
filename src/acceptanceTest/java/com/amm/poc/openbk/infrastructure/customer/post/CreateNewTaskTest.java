package com.amm.poc.openbk.infrastructure.customer.post;

import com.amm.poc.openbk.infrastructure.SpringbootAcceptanceTest;
import com.amm.poc.openbk.infrastructure.task.controller.TaskHttpRequest;
import com.amm.poc.openbk.infrastructure.task.controller.TaskHttpResponse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.net.URI;
import java.net.URISyntaxException;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
public class CreateNewTaskTest extends SpringbootAcceptanceTest {

    @Autowired
    private RestTemplate restTemplate;

    @LocalServerPort
    private int port;

    @Test
    public void shouldCreateNewTask() throws URISyntaxException {
        TaskHttpRequest taskHttpRequest = new TaskHttpRequest("sci-fy article", "lorem ipsum");
        HttpEntity httpRequest = new HttpEntity<TaskHttpRequest>(taskHttpRequest);
        ResponseEntity<TaskHttpResponse> response = restTemplate.postForEntity("http://localhost:%d/v1/task".formatted(port), httpRequest, TaskHttpResponse.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        URI expectedResponse = getLocation();
        assertThat(response.getHeaders().get("Location").get(0)).isEqualTo(expectedResponse.toString());
    }

    private URI getLocation() throws URISyntaxException {
        return new URI("/v1/task/1");
    }
}
