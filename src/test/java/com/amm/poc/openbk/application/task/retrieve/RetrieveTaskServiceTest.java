package com.amm.poc.openbk.application.task.retrieve;

import com.amm.poc.openbk.domain.task.TaskRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static com.amm.poc.openbk.TaskFixtures.*;

class RetrieveTaskServiceTest {
    private TaskRepository taskRepository = Mockito.mock(TaskRepository.class);
    private final RetrieveTaskService retrieveTaskService = new RetrieveTaskService(taskRepository);

    @Test
    void should_return_a_task_given_an_uuid() {
        Mockito.when(taskRepository.findBy(ANY_UUID)).thenReturn(ANY_TASK);
        RetrieveTaskResponse response = retrieveTaskService.execute(ANY_RETRIEVE_TASK_REQUEST);
        Assertions.assertThat(response).isEqualTo(ANY_RETRIEVE_TASK_RESPONSE);
    }
}