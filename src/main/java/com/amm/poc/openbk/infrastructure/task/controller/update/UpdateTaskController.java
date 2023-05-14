package com.amm.poc.openbk.infrastructure.task.controller.update;

import com.amm.poc.openbk.application.task.service.update.UpdateTaskRequest;
import com.amm.poc.openbk.application.task.service.update.UpdateTaskResponse;
import com.amm.poc.openbk.application.task.service.update.UpdateTaskService;
import com.amm.poc.openbk.domain.task.Task;
import com.amm.poc.openbk.infrastructure.task.controller.TaskHttpRequest;
import com.amm.poc.openbk.infrastructure.task.controller.TaskHttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UpdateTaskController implements UpdateTaskControllerInfo {

    private final Logger LOGGER = LoggerFactory.getLogger(UpdateTaskController.class);

    private final UpdateTaskService updateTaskService;

    public UpdateTaskController(UpdateTaskService updateTaskService) {
        this.updateTaskService = updateTaskService;
    }

    @PutMapping("/v1/task/{uuid}")
    @Override
    public ResponseEntity<TaskHttpResponse> execute(
            @PathVariable String uuid,
            @RequestBody TaskHttpRequest httpRequest) {
        LOGGER.info(">>>UpdateTaskController: update task %s".formatted(httpRequest));
        UpdateTaskResponse response = updateTaskService.execute(mapFrom(uuid, httpRequest));
        return ResponseEntity.accepted().body(mapFrom(response));
    }

    private UpdateTaskRequest mapFrom(String uuid, TaskHttpRequest httpRequest) {
        return new UpdateTaskRequest(Task.of(uuid, httpRequest.name(), httpRequest.description(), httpRequest.priority()));
    }

    private TaskHttpResponse mapFrom(UpdateTaskResponse response) {
        return TaskHttpResponse.of(response.task());
    }
}
