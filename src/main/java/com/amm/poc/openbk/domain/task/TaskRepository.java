package com.amm.poc.openbk.domain.task;

import java.util.UUID;

public interface TaskRepository {
    Task save(Task task);

    Task update(Task task);

    Task findBy(UUID uuid);
}
