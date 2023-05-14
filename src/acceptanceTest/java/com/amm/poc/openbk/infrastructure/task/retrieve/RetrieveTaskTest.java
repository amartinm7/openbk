package com.amm.poc.openbk.infrastructure.task.retrieve;

import com.amm.poc.openbk.TaskFixtures;
import com.amm.poc.openbk.infrastructure.SpringbootAcceptanceTest;
import com.amm.poc.openbk.infrastructure.task.controller.TaskHttpRequest;
import com.amm.poc.openbk.infrastructure.task.controller.TaskHttpResponse;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class RetrieveTaskTest extends SpringbootAcceptanceTest {
    @Test
    void should_retrieve_a_task() {
        UUID uuid = insertTask();
        HttpEntity httpRequest = new HttpEntity<TaskHttpRequest>(TaskFixtures.ANY_HTTP_TASK_REQUEST);
        ResponseEntity<TaskHttpResponse> response = restTemplate.exchange(
                "http://localhost:%d/v1/task/%s".formatted(port, uuid),
                HttpMethod.GET,
                httpRequest,
                TaskHttpResponse.class
        );
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(TaskFixtures.ANY_HTTP_TASK_RESPONSE.copyWith(uuid));
    }

    private UUID insertTask() {
        HttpEntity httpRequest = new HttpEntity<TaskHttpRequest>(TaskFixtures.ANY_HTTP_TASK_REQUEST);
        ResponseEntity<TaskHttpResponse> response = restTemplate.postForEntity("http://localhost:%d/v1/task".formatted(port), httpRequest, TaskHttpResponse.class);
        return response.getBody().id();
    }
}
