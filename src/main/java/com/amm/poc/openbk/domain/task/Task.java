package com.amm.poc.openbk.domain.task;

import java.util.UUID;

public record Task(UUID uuid, TaskNameVO name, TaskDescriptionVO description, TaskPriorityVO priority) {
    public static Task of(UUID uuid, String name, String description, int priority) {
        return new Task(
                uuid,
                new TaskNameVO(name),
                new TaskDescriptionVO(description),
                new TaskPriorityVO(priority)
        );
    }

    public record TaskDescriptionVO(String value) {
    }


    public record TaskNameVO(String value) {
    }


    public record TaskPriorityVO(int value) {
    }
}