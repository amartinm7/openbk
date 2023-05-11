package com.amm.poc.openbk.infrastructure.task.repository;

import com.amm.poc.openbk.TaskFixtures;
import com.amm.poc.openbk.domain.task.Task;
import com.amm.poc.openbk.domain.task.TaskDescriptionVO;
import com.amm.poc.openbk.domain.task.TaskNameVO;
import com.amm.poc.openbk.domain.task.TaskRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class JpaTaskRepositoryTest {

    private final CrudJpaTaskRepository crudJpaTaskRepository = Mockito.mock(CrudJpaTaskRepository.class);

    private final TaskRepository taskRepository = new JpaTaskRepository(crudJpaTaskRepository);

    @Test
    public void shouldSaveATask() {
        Mockito.when(crudJpaTaskRepository.save(TaskFixtures.ANY_JPA_TASK)).thenReturn(TaskFixtures.ANY_JPA_TASK);
        Task taskResponse = taskRepository.save(TaskFixtures.ANY_TASK);
        assertThat(taskResponse).isEqualTo(TaskFixtures.ANY_TASK);
    }
}