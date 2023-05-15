package com.amm.poc.openbk.infrastructure.task.controller.retrieve;

import com.amm.poc.openbk.application.task.service.retrieve.RetrieveTaskRequest;
import com.amm.poc.openbk.application.task.service.retrieve.RetrieveTaskResponse;
import com.amm.poc.openbk.application.task.service.retrieve.RetrieveTaskService;
import com.amm.poc.openbk.infrastructure.task.controller.TaskHttpResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;
import java.util.UUID;

@RestController
public class RetrieveTaskController implements RetrieveTaskControllerInfo {

    private final RetrieveTaskService retrieveTaskService;

    public RetrieveTaskController(RetrieveTaskService retrieveTaskService) {
        this.retrieveTaskService = retrieveTaskService;
    }

    @GetMapping(
            path = "/v1/task/{uuid}",
            consumes = "application/json;charset=UTF-8",
            produces = "application/json;charset=UTF-8"
    )
    @Override
    public ResponseEntity<TaskHttpResponse> execute(@PathVariable UUID uuid) {
        try {
            RetrieveTaskResponse response = retrieveTaskService.execute(new RetrieveTaskRequest(uuid));
            return ResponseEntity.ok(mapFrom(response));
        } catch (NoSuchElementException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    private TaskHttpResponse mapFrom(RetrieveTaskResponse response) {
        return TaskHttpResponse.of(response.task());
    }
}
