package com.amm.poc.openbk.domain.task;

import java.util.UUID;

public record Task(UUID uuid, TaskNameVO name, TaskDescriptionVO description, TaskPriorityVO priority) {
}
