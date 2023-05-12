package com.amm.poc.openbk.infrastructure._boostrap;

import com.amm.poc.openbk.application.task.service.CreateNewTaskService;
import com.amm.poc.openbk.domain.task.TaskRepository;
import com.amm.poc.openbk.infrastructure.task.repository.ListCrudJpaTaskRepository;
import com.amm.poc.openbk.infrastructure.task.repository.JpaTaskRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TaskConfiguration {

    @Bean
    public TaskRepository taskRepository(ListCrudJpaTaskRepository listCrudJpaTaskRepository) {
        return new JpaTaskRepository(listCrudJpaTaskRepository);
    }

    @Bean
    public CreateNewTaskService createNewTaskService(TaskRepository taskRepository, UUIDService uuidService) {
        return new CreateNewTaskService(taskRepository, uuidService);
    }
}
