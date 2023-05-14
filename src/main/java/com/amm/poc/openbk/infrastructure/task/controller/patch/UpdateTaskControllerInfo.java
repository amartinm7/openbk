package com.amm.poc.openbk.infrastructure.task.controller.patch;

import com.amm.poc.openbk.infrastructure.task.controller.TaskHttpRequest;
import com.amm.poc.openbk.infrastructure.task.controller.TaskHttpResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Update a given task", description = "Update a given task")
public interface UpdateTaskControllerInfo {

    @Operation(
            summary = "Update a given Task on the system",
            description = "Update a given new task"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = {@Content(schema = @Schema(implementation = TaskHttpResponse.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})
    })
    ResponseEntity<TaskHttpResponse> execute(
            @PathVariable String uuid,
            @RequestBody TaskHttpRequest httpRequest
    );
}
