package com.amm.poc.openbk.infrastructure.task.controller;

import java.util.UUID;

public record TaskHttpResponse(UUID id, String name, String description) {}
