package com.amm.poc.openbk.infrastructure.task.repository;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ListCrudJpaTaskRepository extends ListCrudRepository<JpaTask, UUID> {
}
