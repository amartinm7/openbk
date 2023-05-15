package com.amm.poc.openbk;

import com.amm.poc.openbk.application.task.service.delete.DeleteTaskRequest;
import com.amm.poc.openbk.application.task.service.delete.DeleteTaskResponse;
import com.amm.poc.openbk.application.task.service.retrieve.RetrieveTaskRequest;
import com.amm.poc.openbk.application.task.service.retrieve.RetrieveTaskResponse;
import com.amm.poc.openbk.application.task.service.create.CreateNewTaskRequest;
import com.amm.poc.openbk.application.task.service.update.UpdateTaskRequest;
import com.amm.poc.openbk.application.task.service.update.UpdateTaskResponse;
import com.amm.poc.openbk.domain.task.Task;
import com.amm.poc.openbk.infrastructure.task.controller.TaskHttpRequest;
import com.amm.poc.openbk.infrastructure.task.controller.TaskHttpResponse;
import com.amm.poc.openbk.infrastructure.task.repository.JpaTask;
import org.testcontainers.shaded.com.fasterxml.jackson.core.JsonProcessingException;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.util.UUID;

public class TaskFixtures {
    public static final String taskName = "task#1";
    public static final String taskDescription = "lorem ipsum";
    public static final int taskPriority = 3;
    public static final String ANY_UUID_STR = "3deab62e-2fe2-4f30-aede-96484fa3f738";
    public static final UUID ANY_UUID = UUID.fromString(ANY_UUID_STR);
    public static final Task ANY_TASK = Task.of(ANY_UUID, taskName, taskDescription, taskPriority);
    public static final JpaTask ANY_JPA_TASK = new JpaTask(ANY_UUID, taskName, taskDescription, taskPriority);
    public static final CreateNewTaskRequest ANY_CREATE_NEW_TASK_REQUEST = new CreateNewTaskRequest(taskName, taskDescription, taskPriority);
    public static final UpdateTaskRequest ANY_UPDATE_TASK_REQUEST = new UpdateTaskRequest(ANY_TASK);
    public static final UpdateTaskResponse ANY_UPDATE_TASK_RESPONSE = new UpdateTaskResponse(ANY_TASK);
    public static final RetrieveTaskRequest ANY_RETRIEVE_TASK_REQUEST = new RetrieveTaskRequest(ANY_UUID);
    public static final RetrieveTaskResponse ANY_RETRIEVE_TASK_RESPONSE = new RetrieveTaskResponse(ANY_TASK);
    public static final DeleteTaskRequest ANY_DELETE_TASK_REQUEST = new DeleteTaskRequest(ANY_UUID);
    public static final DeleteTaskResponse ANY_DELETE_TASK_RESPONSE = new DeleteTaskResponse(ANY_TASK);
    public static final TaskHttpRequest ANY_HTTP_TASK_REQUEST = new TaskHttpRequest(taskName, taskDescription, taskPriority);

    public static final TaskHttpResponse ANY_HTTP_TASK_RESPONSE = TaskHttpResponse.of(ANY_TASK);
    public static final String JSON_ANY_TASK_REQUEST = toJSON(ANY_HTTP_TASK_REQUEST);

    private static <T> String toJSON (T object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
