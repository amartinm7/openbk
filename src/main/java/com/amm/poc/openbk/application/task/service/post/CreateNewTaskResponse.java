package com.amm.poc.openbk.application.task.service.post;

import com.amm.poc.openbk.domain.task.Task;

import java.util.UUID;

public record CreateNewTaskResponse(Task task) {
    public UUID uuid() {
        return task.uuid();
    }

    public String name() {
        return task.name().value();
    }

    public String description() {
        return task.description().value();
    }

    public int priority() {
        return task.priority().value();
    }
}
