package com.amm.poc.openbk.infrastructure.task.repository;

import com.amm.poc.openbk.TaskFixtures;
import com.amm.poc.openbk.domain.task.Task;
import com.amm.poc.openbk.domain.task.TaskRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.NoSuchElementException;
import java.util.Optional;

import static com.amm.poc.openbk.TaskFixtures.ANY_UUID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

    @Test
    public void should_return_a_task_by_uuid_() {
        Mockito.when(listCrudJpaTaskRepository.findById(ANY_UUID)).thenReturn(Optional.of(TaskFixtures.ANY_JPA_TASK));
        Task response = taskRepository.findBy(ANY_UUID);
        assertThat(response).isEqualTo(TaskFixtures.ANY_TASK);
    }

    @Test
    public void should_throw_an_exception_when_task_not_exists() {
        Mockito.when(
                listCrudJpaTaskRepository.findById(ANY_UUID)
        ).thenThrow(
                new NoSuchElementException("Task don't exist for id [%s]".formatted(ANY_UUID))
        );
        assertThrows(NoSuchElementException.class, () -> taskRepository.findBy(ANY_UUID));
    }
}