package com.amm.poc.openbk;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import com.amm.poc.openbk.infrastructure.task.repository.JpaTask;

import java.util.Map;

import static java.util.Map.entry;

public class TaskJdbcTemplateHelper {

    protected NamedParameterJdbcTemplate jdbcTemplate;

    public TaskJdbcTemplateHelper(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void createTask() {
        JpaTask request = TaskFixtures.ANY_JPA_TASK;
        Map<String, Object> mapper = Map.ofEntries(
                entry("uuid", request.getUuid()),
                entry("name", request.getName()),
                entry("description", request.getDescription()),
                entry("priority", request.getPriority())
        );
        jdbcTemplate.update(
                "INSERT INTO TASKS (uuid, name, description, priority, created_at, modified_at) " +
                        "VALUES (:uuid::uuid, :name, :description, :priority, now(), now())",
                mapper
        );
    }

    public void deleteTask() {
        JpaTask request = TaskFixtures.ANY_JPA_TASK;
        Map<String, Object> mapper = Map.ofEntries(
                entry("uuid", request.getUuid())
        );
        jdbcTemplate.update(
                "DELETE FROM TASKS WHERE uuid = :uuid::uuid",
                mapper
        );
    }
}
