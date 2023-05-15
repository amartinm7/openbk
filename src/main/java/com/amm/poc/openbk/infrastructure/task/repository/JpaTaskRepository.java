package com.amm.poc.openbk.infrastructure.task.repository;

import com.amm.poc.openbk.domain.task.Task;
import com.amm.poc.openbk.domain.task.TaskRepository;

import java.util.Optional;
import java.util.UUID;

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

    @Override
    public Task update(Task task) {
        return save(task);
    }

    @Override
    public Task findBy(UUID uuid) {
        Optional<JpaTask> response = listCrudJpaTaskRepository.findById(uuid);
        return taskFrom(response.orElseThrow());
    }

    @Override
    public Task delete(UUID uuid) {
        Task task = findBy(uuid);
        listCrudJpaTaskRepository.deleteById(uuid);
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

    private Task taskFrom (JpaTask jpaTask) {
        return Task.of(jpaTask.getUuid(), jpaTask.getName(), jpaTask.getDescription(), jpaTask.getPriority());
    }
}
