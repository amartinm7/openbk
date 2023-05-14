package com.amm.poc.openbk.infrastructure.task.controller.create;

import com.amm.poc.openbk.TaskFixtures;
import com.amm.poc.openbk.application.task.service.create.CreateNewTaskResponse;
import com.amm.poc.openbk.application.task.service.create.CreateNewTaskService;
import com.amm.poc.openbk.infrastructure.task.controller.TaskHttpResponse;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.net.URISyntaxException;

import static org.assertj.core.api.Assertions.assertThat;

class CreateNewTaskControllerTest {

    private final CreateNewTaskService service = Mockito.mock(CreateNewTaskService.class);
    private final CreateNewTaskController controller = new CreateNewTaskController(service);

    @Test
    public void should_save_a_task() throws URISyntaxException {
        CreateNewTaskResponse mockedResponse = new CreateNewTaskResponse(TaskFixtures.ANY_TASK);
        Mockito.when(service.execute(TaskFixtures.ANY_CREATE_NEW_TASK_REQUEST)).thenReturn(mockedResponse);
        URI expectedResponse = getLocation();
        ResponseEntity<TaskHttpResponse> response = controller.execute(TaskFixtures.ANY_HTTP_TASK_REQUEST);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getHeaders().get("Location").get(0)).isEqualTo(expectedResponse.toString());
        assertThat(response.getBody()).isEqualTo(TaskFixtures.ANY_HTTP_TASK_RESPONSE);
    }

    private URI getLocation() throws URISyntaxException {
        return new URI("/v1/task/%s".formatted(TaskFixtures.ANY_UUID.toString()));
    }
}