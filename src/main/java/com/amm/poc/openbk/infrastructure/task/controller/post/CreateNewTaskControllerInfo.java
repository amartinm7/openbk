package com.amm.poc.openbk.infrastructure.task.controller.post;

import com.amm.poc.openbk.application.task.service.CreateNewTaskRequest;
import com.amm.poc.openbk.application.task.service.CreateNewTaskResponse;
import com.amm.poc.openbk.application.task.service.CreateNewTaskService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;

@Tag(name = "CRUD task", description = "CRUD task")
public interface CreateNewTaskControllerInfo {

    ResponseEntity<TaskHttpResponse> execute(@RequestBody TaskHttpRequest httpRequest);
}
