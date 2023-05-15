package com.amm.poc.openbk.infrastructure.task.controller.delete;

import com.amm.poc.openbk.application.task.service.delete.DeleteTaskRequest;
import com.amm.poc.openbk.application.task.service.delete.DeleteTaskResponse;
import com.amm.poc.openbk.application.task.service.delete.DeleteTaskService;
import com.amm.poc.openbk.infrastructure.task.controller.TaskHttpResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class DeleteTaskController implements DeleteTaskControllerInfo {

    private final DeleteTaskService deleteTaskService;

    public DeleteTaskController(DeleteTaskService deleteTaskService) {
        this.deleteTaskService = deleteTaskService;
    }

    @DeleteMapping(path = "/v1/task/{uuid}", consumes = "application/json;charset=UTF-8")
    @Override
    public ResponseEntity<TaskHttpResponse> execute(String uuid) {
        DeleteTaskResponse response = deleteTaskService.execute(new DeleteTaskRequest(UUID.fromString(uuid)));
        return ResponseEntity.ok(mapFrom(response));
    }

    private TaskHttpResponse mapFrom(DeleteTaskResponse response) {
        return TaskHttpResponse.of(response.task());
    }
}
