package com.amm.poc.openbk.infrastructure.task.controller.delete;

import com.amm.poc.openbk.infrastructure.task.controller.TaskHttpResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

@Tag(name = "Delete a new task", description = "Delete a new task")
public interface DeleteTaskControllerInfo {
    @Operation(
            summary = "Delete a new Task on the system",
            description = "Delete a new task"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = TaskHttpResponse.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})
    })
    public ResponseEntity<TaskHttpResponse> execute(@PathVariable String uuid);
}
