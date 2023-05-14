package com.amm.poc.openbk.infrastructure.task.retrieve;

import com.amm.poc.openbk.TaskFixtures;
import com.amm.poc.openbk.infrastructure.SpringbootAcceptanceTest;
import com.amm.poc.openbk.infrastructure.task.controller.TaskHttpRequest;
import com.amm.poc.openbk.infrastructure.task.controller.TaskHttpResponse;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static com.amm.poc.openbk.TaskFixtures.ANY_HTTP_TASK_REQUEST;
import static com.amm.poc.openbk.TaskFixtures.ANY_UUID;
import static org.assertj.core.api.Assertions.assertThat;

public class RetrieveTaskTest extends SpringbootAcceptanceTest {
    @Test
    void should_retrieve_a_task() {
        insertTask();
        HttpEntity httpRequest = new HttpEntity<TaskHttpRequest>(ANY_HTTP_TASK_REQUEST);
        ResponseEntity<TaskHttpResponse> response = restTemplate.exchange(
                "http://localhost:%d/v1/task/%s".formatted(port, ANY_UUID),
                HttpMethod.GET,
                httpRequest,
                TaskHttpResponse.class
        );
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(TaskFixtures.ANY_HTTP_TASK_RESPONSE);
    }
}
