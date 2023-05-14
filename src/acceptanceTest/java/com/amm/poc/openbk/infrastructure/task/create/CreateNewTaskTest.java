package com.amm.poc.openbk.infrastructure.task.create;

import com.amm.poc.openbk.TaskFixtures;
import com.amm.poc.openbk.infrastructure.SpringbootAcceptanceTest;
import com.amm.poc.openbk.infrastructure.task.controller.TaskHttpRequest;
import com.amm.poc.openbk.infrastructure.task.controller.TaskHttpResponse;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

public class CreateNewTaskTest extends SpringbootAcceptanceTest {
    @Test
    public void should_create_a_new_task() {
        HttpEntity<TaskHttpRequest> httpRequest = getHttpEntity(TaskFixtures.ANY_HTTP_TASK_REQUEST);
        ResponseEntity<TaskHttpResponse> response = restTemplate.postForEntity("http://localhost:%d/v1/task".formatted(port), httpRequest, TaskHttpResponse.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getHeaders().get("Location").get(0)).startsWith("/v1/task/");
        assertThat(response.getBody()).isEqualTo(TaskFixtures.ANY_HTTP_TASK_RESPONSE.copyWith(response.getBody().id()));
    }
}
