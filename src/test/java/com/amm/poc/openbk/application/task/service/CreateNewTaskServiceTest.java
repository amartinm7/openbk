package com.amm.poc.openbk.application.task.service;

import com.amm.poc.openbk.TaskFixtures;
import com.amm.poc.openbk.application.task.service.post.CreateNewTaskResponse;
import com.amm.poc.openbk.application.task.service.post.CreateNewTaskService;
import com.amm.poc.openbk.domain.task.TaskRepository;
import com.amm.poc.openbk.infrastructure._boostrap.UUIDService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;

class CreateNewTaskServiceTest {

    private final TaskRepository taskRepository = Mockito.mock(TaskRepository.class);

    private final UUIDService uuidService = Mockito.mock(UUIDService.class);

    private final CreateNewTaskService createNewTaskService = new CreateNewTaskService(taskRepository, uuidService);

    @Test
    public void shouldSaveATask() {
        Mockito.when(taskRepository.save(TaskFixtures.ANY_TASK)).thenReturn(TaskFixtures.ANY_TASK);
        Mockito.when(uuidService.randomUUID()).thenReturn(TaskFixtures.ANY_UUID);
        CreateNewTaskResponse expectedResponse = new CreateNewTaskResponse(TaskFixtures.ANY_TASK);
        CreateNewTaskResponse createNewTaskResponse = createNewTaskService.execute(TaskFixtures.ANY_CREATE_NEW_TASK_REQUEST);
        assertThat(createNewTaskResponse).isEqualTo(expectedResponse);
    }
}