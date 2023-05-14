package com.amm.poc.openbk.infrastructure.task.controller.post;

import com.amm.poc.openbk.application.task.service.post.CreateNewTaskRequest;
import com.amm.poc.openbk.application.task.service.post.CreateNewTaskResponse;
import com.amm.poc.openbk.application.task.service.post.CreateNewTaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class CreateNewTaskController implements CreateNewTaskControllerInfo {

    private final Logger LOGGER = LoggerFactory.getLogger(CreateNewTaskController.class);

    private final CreateNewTaskService service;

    public CreateNewTaskController(CreateNewTaskService service) {
        this.service = service;
    }

    @PostMapping(path = "/v1/task", consumes = "application/json;charset=UTF-8")
    @Override
    public ResponseEntity<TaskHttpResponse> execute(@RequestBody TaskHttpRequest httpRequest){
        try {
            CreateNewTaskResponse serviceResponse = service.execute(mapFrom(httpRequest));
            return ResponseEntity.created(getLocationFrom(serviceResponse)).build();
        } catch (URISyntaxException e) {
            LOGGER.error("Internal Server Error: impossible return response");
            return ResponseEntity.internalServerError().build();
        }
    }

    private CreateNewTaskRequest mapFrom(TaskHttpRequest httpRequest) {
        return new CreateNewTaskRequest(httpRequest.name(), httpRequest.description(), httpRequest.priority());
    }

    private TaskHttpResponse mapFrom(CreateNewTaskResponse serviceResponse) {
        return new TaskHttpResponse(
                serviceResponse.uuid(),
                serviceResponse.name(),
                serviceResponse.description(),
                serviceResponse.priority()
        );
    }

    private URI getLocationFrom(CreateNewTaskResponse serviceResponse) throws URISyntaxException {
        return new URI("/v1/task/%s".formatted(serviceResponse.uuid().toString()));
    }
}
