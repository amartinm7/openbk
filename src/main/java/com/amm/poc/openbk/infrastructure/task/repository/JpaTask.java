package com.amm.poc.openbk.infrastructure.task.repository;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "tasks")
public class JpaTask {
    @Id
    @Column(name = "uuid")
    private UUID uuid;

    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;

    public JpaTask() {
    }

    public JpaTask(UUID uuid, String name, String description) {
        this.uuid = uuid;
        this.name = name;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JpaTask jpaTask = (JpaTask) o;
        return Objects.equals(getUuid(), jpaTask.getUuid()) && Objects.equals(getName(), jpaTask.getName()) && Objects.equals(getDescription(), jpaTask.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUuid(), getName(), getDescription());
    }

    @Override
    public String toString() {
        return "JpaTask{" +
                "uuid=" + uuid +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
