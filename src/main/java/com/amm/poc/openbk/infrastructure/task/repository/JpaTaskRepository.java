package com.amm.poc.openbk.infrastructure.task.repository;

import com.amm.poc.openbk.domain.task.Task;
import com.amm.poc.openbk.domain.task.TaskRepository;

public class JpaTaskRepository implements TaskRepository {

    private final CrudJpaTaskRepository crudJpaTaskRepository;

    public JpaTaskRepository(CrudJpaTaskRepository crudJpaTaskRepository) {

        this.crudJpaTaskRepository = crudJpaTaskRepository;
    }

    @Override
    public Task save(Task task) {
        crudJpaTaskRepository.save(jpaTaskFrom(task));
        return task;
    }

    private JpaTask jpaTaskFrom (Task task) {
        return new JpaTask(task.uuid(), task.name().value(), task.description().value());
    }
}
