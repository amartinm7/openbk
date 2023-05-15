package com.amm.poc.openbk.application.task.delete;

import com.amm.poc.openbk.domain.task.Task;
import com.amm.poc.openbk.domain.task.TaskRepository;

public class DeleteTaskService {
    private TaskRepository taskRepository;

    public DeleteTaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public DeleteTaskResponse execute (DeleteTaskRequest request) {
        Task task = taskRepository.delete(request.uuid());
        return new DeleteTaskResponse(task);
    }
}
