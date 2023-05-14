package com.amm.poc.openbk.infrastructure.task.post;

import com.amm.poc.openbk.infrastructure.SpringbootAcceptanceTest;
import com.amm.poc.openbk.infrastructure.task.controller.TaskHttpRequest;
import com.amm.poc.openbk.infrastructure.task.controller.TaskHttpResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

public class CreateNewTaskTest extends SpringbootAcceptanceTest {

    @Autowired
    private RestTemplate restTemplate;

    @LocalServerPort
    private int port;

    @Test
    public void should_create_a_new_task() {
        TaskHttpRequest taskHttpRequest = new TaskHttpRequest("sci-fy article", "lorem ipsum", 3);
        HttpEntity httpRequest = new HttpEntity<TaskHttpRequest>(taskHttpRequest);
        ResponseEntity<TaskHttpResponse> response = restTemplate.postForEntity("http://localhost:%d/v1/task".formatted(port), httpRequest, TaskHttpResponse.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getHeaders().get("Location").get(0)).startsWith("/v1/task/");
        // assertThat(response.getBody()).isEqualTo(TaskFixtures.ANY_HTTP_TASK_RESPONSE);
    }
}
