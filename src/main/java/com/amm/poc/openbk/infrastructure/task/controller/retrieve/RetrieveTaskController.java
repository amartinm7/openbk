package com.amm.poc.openbk.infrastructure.task.controller.retrieve;

import com.amm.poc.openbk.application.task.retrieve.RetrieveTaskRequest;
import com.amm.poc.openbk.application.task.retrieve.RetrieveTaskResponse;
import com.amm.poc.openbk.application.task.retrieve.RetrieveTaskService;
import com.amm.poc.openbk.infrastructure.task.controller.TaskHttpResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class RetrieveTaskController implements RetrieveTaskControllerInfo {

    private final RetrieveTaskService retrieveTaskService;

    public RetrieveTaskController(RetrieveTaskService retrieveTaskService) {
        this.retrieveTaskService = retrieveTaskService;
    }

    @GetMapping(path = "/v1/task/{uuid}", consumes = "application/json;charset=UTF-8")
    @Override
    public ResponseEntity<TaskHttpResponse> execute(@PathVariable String uuid) {
        RetrieveTaskResponse response = retrieveTaskService.execute(new RetrieveTaskRequest(UUID.fromString(uuid)));
        return ResponseEntity.ok(mapFrom(response));
    }

    private TaskHttpResponse mapFrom(RetrieveTaskResponse response) {
        return TaskHttpResponse.of(response.task());
    }
}
