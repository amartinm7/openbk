package com.amm.poc.openbk.application.task.service.patch;

import com.amm.poc.openbk.TaskFixtures;
import com.amm.poc.openbk.domain.task.TaskRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;

class UpdateTaskServiceTest {
    private UpdateTaskResponse expected = TaskFixtures.ANY_UPDATE_TASK_RESPONSE;
    private TaskRepository repository = Mockito.mock(TaskRepository.class);
    private final UpdateTaskService updateTaskService = new UpdateTaskService(repository);

    @Test
    void should_update_a_task() {
        Mockito.when(repository.update(TaskFixtures.ANY_TASK)).thenReturn(TaskFixtures.ANY_TASK);
        UpdateTaskResponse response = updateTaskService.execute(TaskFixtures.ANY_UPDATE_TASK_REQUEST);
        assertThat(response).isEqualTo(expected);
    }
}