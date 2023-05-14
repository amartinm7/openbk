package com.amm.poc.openbk;

import com.amm.poc.openbk.application.task.service.patch.UpdateTaskRequest;
import com.amm.poc.openbk.application.task.service.patch.UpdateTaskResponse;
import com.amm.poc.openbk.application.task.service.post.CreateNewTaskRequest;
import com.amm.poc.openbk.domain.task.Task;
import com.amm.poc.openbk.infrastructure.task.controller.post.TaskHttpRequest;
import com.amm.poc.openbk.infrastructure.task.repository.JpaTask;

import java.util.UUID;

public class TaskFixtures {
    public static final String taskName = "task#1";
    public static final String taskDescription = "lorem ipsum";
    public static final int taskPriority = 3;
    public static final String UUID_STR = "3deab62e-2fe2-4f30-aede-96484fa3f738";
    public static final UUID ANY_UUID = UUID.fromString(UUID_STR);
    public static final Task ANY_TASK = Task.of(ANY_UUID, taskName, taskDescription, taskPriority);
    public static final JpaTask ANY_JPA_TASK = new JpaTask(ANY_UUID, taskName, taskDescription, taskPriority);
    public static final CreateNewTaskRequest ANY_CREATE_NEW_TASK_REQUEST = new CreateNewTaskRequest(taskName, taskDescription, taskPriority);
    public static final UpdateTaskRequest ANY_UPDATE_TASK_REQUEST = new UpdateTaskRequest(ANY_TASK);
    public static final UpdateTaskResponse ANY_UPDATE_TASK_RESPONSE = new UpdateTaskResponse(ANY_TASK);
    public static final TaskHttpRequest HTTP_TASK_REQUEST = new TaskHttpRequest(taskName, taskDescription, taskPriority);
}
