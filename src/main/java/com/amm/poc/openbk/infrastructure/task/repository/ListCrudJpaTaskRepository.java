package com.amm.poc.openbk.infrastructure.task.repository;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Hidden
@Repository
public interface ListCrudJpaTaskRepository extends ListCrudRepository<JpaTask, UUID> {
}
