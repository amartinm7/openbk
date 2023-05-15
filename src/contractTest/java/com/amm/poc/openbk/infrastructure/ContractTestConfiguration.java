package com.amm.poc.openbk.infrastructure;

import com.amm.poc.openbk.application.task.service.create.CreateNewTaskService;
import com.amm.poc.openbk.application.task.service.delete.DeleteTaskService;
import com.amm.poc.openbk.application.task.service.retrieve.RetrieveTaskService;
import com.amm.poc.openbk.application.task.service.update.UpdateTaskService;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ContractTestConfiguration {

    @MockBean
    public RetrieveTaskService retrieveTaskService;

    @MockBean
    private DeleteTaskService deleteTaskService;

    @MockBean
    private UpdateTaskService updateTaskService;

    @MockBean
    private CreateNewTaskService createNewTaskService;
}
