package com.amm.poc.openbk.infrastructure.task.controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record TaskHttpRequest(
        @JsonProperty("name") String name,
        @JsonProperty("description") String description,
        @JsonProperty("priority") int priority) {
}
