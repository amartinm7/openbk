package com.amm.poc.openbk.infrastructure;

import com.amm.poc.openbk.application.task.service.retrieve.RetrieveTaskService;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ContractTestConfiguration {

    @MockBean
    public RetrieveTaskService retrieveTaskService;
}
