package com.amm.poc.openbk.infrastructure.task.controller;

import com.amm.poc.openbk.TaskFixtures;
import com.amm.poc.openbk.application.task.service.CreateNewTaskResponse;
import com.amm.poc.openbk.application.task.service.CreateNewTaskService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class CreateNewTaskControllerTest {

    private final CreateNewTaskService service = Mockito.mock(CreateNewTaskService.class);
    private final CreateNewTaskController controller = new CreateNewTaskController(service);

    @Test
    public void shouldSaveATask() throws URISyntaxException {
        CreateNewTaskResponse mockedResponse = new CreateNewTaskResponse(TaskFixtures.ANY_TASK);
        Mockito.when(service.execute(TaskFixtures.ANY_TASK_REQUEST)).thenReturn(mockedResponse);
        URI expectedResponse = getLocation();
        ResponseEntity<TaskHttpResponse> response = controller.execute(TaskFixtures.HTTP_TASK_REQUEST);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getHeaders().get("Location").get(0)).isEqualTo(expectedResponse.toString());
    }

    private URI getLocation() throws URISyntaxException {
        return new URI("/v1/task/%s".formatted(TaskFixtures.ANY_UUID.toString()));
    }
}