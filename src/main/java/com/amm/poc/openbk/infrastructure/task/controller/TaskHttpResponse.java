package com.amm.poc.openbk.infrastructure.task.controller;

import com.amm.poc.openbk.domain.task.Task;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

@JsonIgnoreProperties(ignoreUnknown = true)
public record TaskHttpResponse(
        @JsonProperty("id") UUID id,
        @JsonProperty("name") String name,
        @JsonProperty("description") String description,
        @JsonProperty("priority") int priority
) {
    public static TaskHttpResponse of (Task task) {
        return new TaskHttpResponse(
                task.uuid(), task.name().value(), task.description().value(), task.priority().value()
        );
    }

    public TaskHttpResponse copyWith(UUID id) {
        return new TaskHttpResponse (id, this.name, this.description, this.priority);
    }
}
