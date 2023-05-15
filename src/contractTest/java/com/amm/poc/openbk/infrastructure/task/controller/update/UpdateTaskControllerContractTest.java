package com.amm.poc.openbk.infrastructure.task.controller.update;

import com.amm.poc.openbk.TaskFixtures;
import com.amm.poc.openbk.application.task.service.update.UpdateTaskService;
import com.amm.poc.openbk.infrastructure.SpringbootContractTest;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.NoSuchElementException;

import static com.amm.poc.openbk.TaskFixtures.*;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class UpdateTaskControllerContractTest extends SpringbootContractTest {

    @Autowired
    private UpdateTaskService updateTaskService;

    @Test
    public void should_update_a_task_given_an_uuid() throws Exception {
        Mockito.when(updateTaskService.execute(ANY_UPDATE_TASK_REQUEST)).thenReturn(ANY_UPDATE_TASK_RESPONSE);
        mvc.perform(MockMvcRequestBuilders.put("/v1/task/%s".formatted(TaskFixtures.ANY_UUID))
                .content(JSON_ANY_TASK_REQUEST)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(TaskFixtures.ANY_HTTP_TASK_RESPONSE.id().toString())))
                .andExpect(jsonPath("$.name", is(TaskFixtures.ANY_HTTP_TASK_RESPONSE.name())))
                .andExpect(jsonPath("$.description", is(TaskFixtures.ANY_HTTP_TASK_RESPONSE.description())))
                .andExpect(jsonPath("$.priority", is(TaskFixtures.ANY_HTTP_TASK_RESPONSE.priority())));
    }
}
