package com.amm.poc.openbk.infrastructure.task.controller.retrieve;

import com.amm.poc.openbk.TaskFixtures;
import com.amm.poc.openbk.application.task.service.retrieve.RetrieveTaskService;
import com.amm.poc.openbk.infrastructure.task.controller.TaskHttpResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.NoSuchElementException;

import static com.amm.poc.openbk.TaskFixtures.*;

class RetrieveTaskControllerTest {
    private RetrieveTaskService retrieveTaskService = Mockito.mock(RetrieveTaskService.class);
    private final RetrieveTaskController retrieveTaskController = new RetrieveTaskController(retrieveTaskService);

    @Test
    void should_return_a_task_given_an_uuid() {
        Mockito.when(retrieveTaskService.execute(ANY_RETRIEVE_TASK_REQUEST)).thenReturn(ANY_RETRIEVE_TASK_RESPONSE);
        ResponseEntity<TaskHttpResponse> response = retrieveTaskController.execute(ANY_UUID);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(response.getBody()).isEqualTo(ANY_HTTP_TASK_RESPONSE);
    }

    @Test
    void should_return_a_not_found_message_given_an_uuid() {
        Mockito.when(retrieveTaskService.execute(ANY_RETRIEVE_TASK_REQUEST)).thenThrow(new NoSuchElementException("Element not exists"));
        ResponseEntity<TaskHttpResponse> response = retrieveTaskController.execute(ANY_UUID);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }
}