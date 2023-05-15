package com.amm.poc.openbk.infrastructure.task.controller.delete;

import com.amm.poc.openbk.application.task.delete.DeleteTaskRequest;
import com.amm.poc.openbk.application.task.delete.DeleteTaskResponse;
import com.amm.poc.openbk.application.task.delete.DeleteTaskService;
import com.amm.poc.openbk.infrastructure.task.controller.TaskHttpResponse;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public class DeleteTaskController implements DeleteTaskControllerInfo {

    private final DeleteTaskService deleteTaskService;

    public DeleteTaskController(DeleteTaskService deleteTaskService) {
        this.deleteTaskService = deleteTaskService;
    }

    @Override
    public ResponseEntity<TaskHttpResponse> execute(String uuid) {
        DeleteTaskResponse response = deleteTaskService.execute(new DeleteTaskRequest(UUID.fromString(uuid)));
        return ResponseEntity.ok(mapFrom(response));
    }

    private TaskHttpResponse mapFrom(DeleteTaskResponse response) {
        return TaskHttpResponse.of(response.task());
    }
}
