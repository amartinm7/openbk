package com.amm.poc.openbk.application.task.service.update;

import com.amm.poc.openbk.domain.task.Task;
import com.amm.poc.openbk.domain.task.TaskRepository;

public class UpdateTaskService {
    private TaskRepository repository;

    public UpdateTaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public UpdateTaskResponse execute(UpdateTaskRequest request) {
        Task task = repository.update(request.task());
        return mapFrom(task);
    }

    private UpdateTaskResponse mapFrom(Task task) {
        return new UpdateTaskResponse(task);
    }
}

