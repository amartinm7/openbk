package com.amm.poc.openbk.application.task.service.create;

import com.amm.poc.openbk.domain.task.Task;
import com.amm.poc.openbk.domain.task.TaskRepository;
import com.amm.poc.openbk.infrastructure._boostrap.UUIDService;

public class CreateNewTaskService {
    private final TaskRepository taskRepository;

    private final UUIDService uuidService;

    public CreateNewTaskService(TaskRepository taskRepository, UUIDService uuidService) {
        this.taskRepository = taskRepository;
        this.uuidService = uuidService;
    }

    public CreateNewTaskResponse execute(CreateNewTaskRequest request) {
        Task task = taskRepository.save(taskFrom(request));
        return new CreateNewTaskResponse(task);
    }

    private Task taskFrom(CreateNewTaskRequest request) {
        return Task.of(uuidService.randomUUID().toString(), request.name(), request.description(), request.priority());
    }
}
