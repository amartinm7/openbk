package com.amm.poc.openbk.infrastructure.task.controller.patch;

import com.amm.poc.openbk.application.task.service.patch.UpdateTaskRequest;
import com.amm.poc.openbk.application.task.service.patch.UpdateTaskResponse;
import com.amm.poc.openbk.application.task.service.patch.UpdateTaskService;
import com.amm.poc.openbk.domain.task.Task;
import com.amm.poc.openbk.infrastructure.task.controller.post.TaskHttpRequest;
import com.amm.poc.openbk.infrastructure.task.controller.post.TaskHttpResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public class UpdateTaskController implements UpdateTaskControllerInfo {

    private final UpdateTaskService updateTaskService;

    public UpdateTaskController(UpdateTaskService updateTaskService) {
        this.updateTaskService = updateTaskService;
    }

    @PatchMapping("/v1/task/${uuid}")
    @Override
    public ResponseEntity<TaskHttpResponse> execute(
            @PathVariable String uuid,
            @RequestBody TaskHttpRequest httpRequest) {
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
