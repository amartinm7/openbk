package com.amm.poc.openbk.infrastructure.task.delete;

import com.amm.poc.openbk.TaskFixtures;
import com.amm.poc.openbk.infrastructure.SpringbootAcceptanceTest;
import com.amm.poc.openbk.infrastructure.task.controller.TaskHttpResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static com.amm.poc.openbk.TaskFixtures.ANY_UUID;
import static org.assertj.core.api.Assertions.assertThat;

public class DeleteTaskTest extends SpringbootAcceptanceTest {

    @BeforeEach
    void setup() {
        deleteTask();
        createTask();
    }

    @Test
    void should_remove_a_task_given_a_uuid() {
        ResponseEntity<TaskHttpResponse> response = restTemplate.exchange(
                "http://localhost:%d/v1/task/%s".formatted(port, ANY_UUID),
                HttpMethod.DELETE,
                getDefaultHttpEntity(),
                TaskHttpResponse.class
        );
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(TaskFixtures.ANY_HTTP_TASK_RESPONSE);
    }
}
