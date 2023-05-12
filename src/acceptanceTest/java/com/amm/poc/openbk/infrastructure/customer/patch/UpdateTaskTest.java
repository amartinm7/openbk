package com.amm.poc.openbk.infrastructure.customer.patch;

import com.amm.poc.openbk.infrastructure.SpringbootAcceptanceTest;
import com.amm.poc.openbk.infrastructure.task.controller.post.TaskHttpRequest;
import com.amm.poc.openbk.infrastructure.task.controller.post.TaskHttpResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
public class UpdateTaskTest extends SpringbootAcceptanceTest {

    @Autowired
    private RestTemplate restTemplate;

    @LocalServerPort
    private int port;

    @Test
    public void should_update_a_task() {
        UUID uuid = UUID.fromString("3deab62e-2fe2-4f30-aede-96484fa3f738");
        TaskHttpRequest taskHttpRequest = new TaskHttpRequest("sci-fy article", "lorem ipsum", 3);
        HttpEntity httpRequest = new HttpEntity<TaskHttpRequest>(taskHttpRequest);
        ResponseEntity<TaskHttpResponse> response =
                restTemplate.patchForObject(
                        "http://localhost:%d/v1/task/%s".formatted(port, uuid),
                        httpRequest,
                        ResponseEntity.class
                );
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getHeaders().get("Location").get(0)).startsWith("/v1/task/");
    }
}
