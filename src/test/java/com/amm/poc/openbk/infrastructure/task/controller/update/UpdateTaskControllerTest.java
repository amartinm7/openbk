package com.amm.poc.openbk.infrastructure.task.controller.update;

import com.amm.poc.openbk.application.task.service.update.UpdateTaskService;
import com.amm.poc.openbk.infrastructure.task.controller.TaskHttpResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static com.amm.poc.openbk.TaskFixtures.*;

class UpdateTaskControllerTest {
    private UpdateTaskService updateTaskService = Mockito.mock(UpdateTaskService.class);

    private final UpdateTaskController updateTaskController = new UpdateTaskController(updateTaskService);

    @Test
    public void should_update_a_task() {
        Mockito.when(updateTaskService.execute(ANY_UPDATE_TASK_REQUEST)).thenReturn(ANY_UPDATE_TASK_RESPONSE);
        ResponseEntity<TaskHttpResponse> response = updateTaskController.execute(ANY_UUID, ANY_HTTP_TASK_REQUEST);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(response.getBody()).isEqualTo(ANY_HTTP_TASK_RESPONSE);
    }
}