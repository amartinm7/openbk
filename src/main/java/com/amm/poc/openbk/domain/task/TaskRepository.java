package com.amm.poc.openbk.domain.task;

public interface TaskRepository {
    Task save(Task task);

    Task update(Task task);
}
