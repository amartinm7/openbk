package com.amm.poc.openbk.infrastructure.task.controller.delete;

import com.amm.poc.openbk.application.task.service.delete.DeleteTaskRequest;
import com.amm.poc.openbk.application.task.service.delete.DeleteTaskResponse;
import com.amm.poc.openbk.application.task.service.delete.DeleteTaskService;
import com.amm.poc.openbk.infrastructure.task.controller.TaskHttpResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;
import java.util.UUID;

@RestController
public class DeleteTaskController implements DeleteTaskControllerInfo {

    private final DeleteTaskService deleteTaskService;

    public DeleteTaskController(DeleteTaskService deleteTaskService) {
        this.deleteTaskService = deleteTaskService;
    }

    @DeleteMapping(
            path = "/v1/task/{uuid}",
            consumes = "application/json;charset=UTF-8",
            produces = "application/json;charset=UTF-8"
    )
    @Override
    public ResponseEntity<TaskHttpResponse> execute(@PathVariable UUID uuid) {
        try {
            DeleteTaskResponse response = deleteTaskService.execute(new DeleteTaskRequest(uuid));
            return ResponseEntity.ok(mapFrom(response));
        } catch (NoSuchElementException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    private TaskHttpResponse mapFrom(DeleteTaskResponse response) {
        return TaskHttpResponse.of(response.task());
    }
}
