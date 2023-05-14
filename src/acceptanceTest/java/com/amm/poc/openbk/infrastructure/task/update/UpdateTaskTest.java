package com.amm.poc.openbk.infrastructure.task.update;

import com.amm.poc.openbk.TaskFixtures;
import com.amm.poc.openbk.infrastructure.SpringbootAcceptanceTest;
import com.amm.poc.openbk.infrastructure.task.controller.TaskHttpRequest;
import com.amm.poc.openbk.infrastructure.task.controller.TaskHttpResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;


public class UpdateTaskTest extends SpringbootAcceptanceTest {

    @Autowired
    private RestTemplate restTemplate;

    @LocalServerPort
    private int port;

    @Test
    public void should_update_a_task() {
        HttpEntity httpRequest = new HttpEntity<TaskHttpRequest>(TaskFixtures.ANY_HTTP_TASK_REQUEST);

        ResponseEntity<TaskHttpResponse> response = restTemplate.exchange(
                "http://localhost:%d/v1/task/%s".formatted(port, TaskFixtures.ANY_UUID_STR),
                HttpMethod.PUT,
                httpRequest,
                TaskHttpResponse.class
        );
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.ACCEPTED);
        assertThat(response.getBody()).isEqualTo(TaskFixtures.ANY_HTTP_TASK_RESPONSE);
    }
}