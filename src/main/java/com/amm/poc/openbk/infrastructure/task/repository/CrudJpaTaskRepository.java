package com.amm.poc.openbk.infrastructure.task.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CrudJpaTaskRepository extends CrudRepository<JpaTask, Long> {
}
