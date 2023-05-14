package com.amm.poc.openbk.infrastructure.task.controller.patch;

import com.amm.poc.openbk.TaskFixtures;
import com.amm.poc.openbk.application.task.service.patch.UpdateTaskService;
import com.amm.poc.openbk.infrastructure.task.controller.post.TaskHttpResponse;
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
        ResponseEntity<TaskHttpResponse> response = updateTaskController.execute(UUID_STR, HTTP_TASK_REQUEST);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.ACCEPTED);
        Assertions.assertThat(response.getBody()).isEqualTo(HTTP_TASK_RESPONSE);
    }
}