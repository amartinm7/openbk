package com.amm.poc.openbk.infrastructure.task.repository;

import com.amm.poc.openbk.domain.task.Task;
import com.amm.poc.openbk.domain.task.TaskRepository;

public class JpaTaskRepository implements TaskRepository {

    private final ListCrudJpaTaskRepository listCrudJpaTaskRepository;

    public JpaTaskRepository(ListCrudJpaTaskRepository listCrudJpaTaskRepository) {
        this.listCrudJpaTaskRepository = listCrudJpaTaskRepository;
    }

    @Override
    public Task save(Task task) {
        listCrudJpaTaskRepository.save(jpaTaskFrom(task));
        return task;
    }

    private JpaTask jpaTaskFrom(Task task) {
        return new JpaTask(
                task.uuid(),
                task.name().value(),
                task.description().value(),
                task.priority().value()
        );
    }
}
