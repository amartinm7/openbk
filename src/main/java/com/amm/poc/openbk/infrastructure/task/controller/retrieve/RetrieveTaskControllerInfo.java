package com.amm.poc.openbk.infrastructure.task.controller.retrieve;

import com.amm.poc.openbk.infrastructure.task.controller.TaskHttpResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@Tag(name = "Retrieve a new task", description = "Retrieve a new task")
public interface RetrieveTaskControllerInfo {

    @Operation(
            summary = "Retrieve a new Task on the system",
            description = "Retrieve a new task"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = TaskHttpResponse.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})
    })
    ResponseEntity<TaskHttpResponse> execute(@PathVariable UUID uuid);
}
