package com.amm.poc.openbk.application.task.service;

import com.amm.poc.openbk.domain.task.Task;
import com.amm.poc.openbk.domain.task.TaskDescriptionVO;
import com.amm.poc.openbk.domain.task.TaskNameVO;
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
        return new Task(uuidService.randomUUID(), new TaskNameVO(request.name()), new TaskDescriptionVO(request.description()));
    }
}
