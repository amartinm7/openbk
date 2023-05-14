package com.amm.poc.openbk.infrastructure.task.repository;

import com.amm.poc.openbk.TaskFixtures;
import com.amm.poc.openbk.domain.task.Task;
import com.amm.poc.openbk.domain.task.TaskRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;

class JpaTaskRepositoryTest {

    private final ListCrudJpaTaskRepository listCrudJpaTaskRepository = Mockito.mock(ListCrudJpaTaskRepository.class);

    private final TaskRepository taskRepository = new JpaTaskRepository(listCrudJpaTaskRepository);

    @Test
    public void should_save_a_task() {
        Mockito.when(listCrudJpaTaskRepository.save(TaskFixtures.ANY_JPA_TASK)).thenReturn(TaskFixtures.ANY_JPA_TASK);
        Task taskResponse = taskRepository.save(TaskFixtures.ANY_TASK);
        assertThat(taskResponse).isEqualTo(TaskFixtures.ANY_TASK);
    }

    @Test
    public void should_update_a_task() {
        Mockito.when(listCrudJpaTaskRepository.save(TaskFixtures.ANY_JPA_TASK)).thenReturn(TaskFixtures.ANY_JPA_TASK);
        Task response = taskRepository.update(TaskFixtures.ANY_TASK);
        assertThat(response).isEqualTo(TaskFixtures.ANY_TASK);
    }
}