package com.amm.poc.openbk.infrastructure.task.controller.delete;

import com.amm.poc.openbk.TaskFixtures;
import com.amm.poc.openbk.application.task.service.delete.DeleteTaskService;
import com.amm.poc.openbk.infrastructure.task.controller.TaskHttpResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.NoSuchElementException;

import static com.amm.poc.openbk.TaskFixtures.*;

class DeleteTaskControllerTest {
    private DeleteTaskService deleteTaskService = Mockito.mock(DeleteTaskService.class);
    private final DeleteTaskController deleteTaskController = new DeleteTaskController(deleteTaskService);
    @Test
    void should_delete_a_task_given_an_uuid() {
        Mockito.when(deleteTaskService.execute(ANY_DELETE_TASK_REQUEST)).thenReturn(ANY_DELETE_TASK_RESPONSE);
        ResponseEntity<TaskHttpResponse> response = deleteTaskController.execute(ANY_UUID);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(response.getBody()).isEqualTo(ANY_HTTP_TASK_RESPONSE);
    }

    @Test
    void should_return_a_not_found_message_given_an_uuid() {
        Mockito.when(deleteTaskService.execute(ANY_DELETE_TASK_REQUEST)).thenThrow(new NoSuchElementException("Element not exists"));
        ResponseEntity<TaskHttpResponse> response = deleteTaskController.execute(ANY_UUID);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }
}