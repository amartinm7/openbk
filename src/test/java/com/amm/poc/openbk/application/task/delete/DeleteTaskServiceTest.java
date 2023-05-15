package com.amm.poc.openbk.application.task.delete;

import com.amm.poc.openbk.TaskFixtures;
import com.amm.poc.openbk.domain.task.TaskRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static com.amm.poc.openbk.TaskFixtures.ANY_DELETE_TASK_REQUEST;
import static com.amm.poc.openbk.TaskFixtures.ANY_DELETE_TASK_RESPONSE;
import static org.assertj.core.api.Assertions.assertThat;

class DeleteTaskServiceTest {
    private TaskRepository taskRepository = Mockito.mock(TaskRepository.class);
    private final DeleteTaskService deleteTaskService = new DeleteTaskService(taskRepository);

    @Test
    void should_delete_a_task_given_an_uuid() {
        Mockito.when(taskRepository.delete(TaskFixtures.ANY_UUID)).thenReturn(TaskFixtures.ANY_TASK);
        DeleteTaskResponse response = deleteTaskService.execute(ANY_DELETE_TASK_REQUEST);
        assertThat(response).isEqualTo(ANY_DELETE_TASK_RESPONSE);
    }
}