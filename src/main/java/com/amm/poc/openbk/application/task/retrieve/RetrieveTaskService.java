package com.amm.poc.openbk.application.task.retrieve;

import com.amm.poc.openbk.domain.task.Task;
import com.amm.poc.openbk.domain.task.TaskRepository;

public class RetrieveTaskService {
    private TaskRepository taskRepository;

    public RetrieveTaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public RetrieveTaskResponse execute(RetrieveTaskRequest request) {
        Task task = taskRepository.findBy(request.uuid());
        return new RetrieveTaskResponse(task);
    }
}
