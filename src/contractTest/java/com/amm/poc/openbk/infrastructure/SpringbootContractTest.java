package com.amm.poc.openbk.infrastructure;

import com.amm.poc.openbk.MyDockerContainer;
import com.amm.poc.openbk.OpenbkApplication;
import com.amm.poc.openbk.TaskJdbcTemplateHelper;
import com.amm.poc.openbk.infrastructure.ContractTestConfiguration;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = {ContractTestConfiguration.class, OpenbkApplication.class})
@AutoConfigureMockMvc
public abstract class SpringbootContractTest {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    protected MockMvc mvc;

    static {
        MyDockerContainer dockerContainer = new MyDockerContainer();
        dockerContainer.start();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> dockerContainer.stop()));
    }

    protected void createTask() {
        TaskJdbcTemplateHelper taskJdbcTemplateHelper = new TaskJdbcTemplateHelper(jdbcTemplate);
        taskJdbcTemplateHelper.createTask();
    }

    protected void deleteTask() {
        TaskJdbcTemplateHelper taskJdbcTemplateHelper = new TaskJdbcTemplateHelper(jdbcTemplate);
        taskJdbcTemplateHelper.deleteTask();
    }
}
