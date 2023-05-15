package com.amm.poc.openbk.infrastructure.task.controller.create;

import com.amm.poc.openbk.TaskFixtures;
import com.amm.poc.openbk.application.task.service.create.CreateNewTaskService;
import com.amm.poc.openbk.application.task.service.update.UpdateTaskService;
import com.amm.poc.openbk.infrastructure.SpringbootContractTest;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.amm.poc.openbk.TaskFixtures.*;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class CreateTaskControllerContractTest extends SpringbootContractTest {

    @Autowired
    private CreateNewTaskService createNewTaskService;

    @Test
    public void should_create_a_task_given_an_uuid() throws Exception {
        Mockito.when(createNewTaskService.execute(ANY_CREATE_NEW_TASK_REQUEST)).thenReturn(ANY_CREATE_NEW_TASK_RESPONSE);
        mvc.perform(MockMvcRequestBuilders.post("/v1/task")
                .content(JSON_ANY_TASK_REQUEST)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name", is(TaskFixtures.ANY_HTTP_TASK_RESPONSE.name())))
                .andExpect(jsonPath("$.description", is(TaskFixtures.ANY_HTTP_TASK_RESPONSE.description())))
                .andExpect(jsonPath("$.priority", is(TaskFixtures.ANY_HTTP_TASK_RESPONSE.priority())));
    }

    @Test
    public void should_retrieve_a_bad_request() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/v1/task")
                        .content("")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}
