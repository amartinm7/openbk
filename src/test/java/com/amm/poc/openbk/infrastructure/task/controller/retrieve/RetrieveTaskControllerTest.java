package com.amm.poc.openbk.infrastructure.task.controller.retrieve;

import com.amm.poc.openbk.TaskFixtures;
import com.amm.poc.openbk.application.task.retrieve.RetrieveTaskService;
import com.amm.poc.openbk.infrastructure.task.controller.TaskHttpResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static com.amm.poc.openbk.TaskFixtures.ANY_RETRIEVE_TASK_REQUEST;
import static com.amm.poc.openbk.TaskFixtures.ANY_RETRIEVE_TASK_RESPONSE;

class RetrieveTaskControllerTest {
    private RetrieveTaskService retrieveTaskService = Mockito.mock(RetrieveTaskService.class);
    private final RetrieveTaskController retrieveTaskController = new RetrieveTaskController(retrieveTaskService);

    @Test
    void should_return_a_task_give_an_uuid() {
        Mockito.when(retrieveTaskService.execute(ANY_RETRIEVE_TASK_REQUEST)).thenReturn(ANY_RETRIEVE_TASK_RESPONSE);
        ResponseEntity<TaskHttpResponse> response = retrieveTaskController.execute(TaskFixtures.ANY_UUID_STR);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(response.getBody()).isEqualTo(TaskFixtures.ANY_HTTP_TASK_RESPONSE);
    }
}