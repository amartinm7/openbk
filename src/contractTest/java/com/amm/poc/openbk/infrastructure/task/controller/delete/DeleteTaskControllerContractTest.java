package com.amm.poc.openbk.infrastructure.task.controller.delete;

import com.amm.poc.openbk.TaskFixtures;
import com.amm.poc.openbk.application.task.service.delete.DeleteTaskService;
import com.amm.poc.openbk.infrastructure.SpringbootContractTest;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.NoSuchElementException;

import static com.amm.poc.openbk.TaskFixtures.ANY_DELETE_TASK_REQUEST;
import static com.amm.poc.openbk.TaskFixtures.ANY_DELETE_TASK_RESPONSE;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class DeleteTaskControllerContractTest extends SpringbootContractTest {

    @Autowired
    private DeleteTaskService deleteTaskService;

    @Test
    public void should_delete_a_task_given_an_uuid() throws Exception {
        Mockito.when(deleteTaskService.execute(ANY_DELETE_TASK_REQUEST)).thenReturn(ANY_DELETE_TASK_RESPONSE);
        mvc.perform(MockMvcRequestBuilders.delete("/v1/task/%s".formatted(TaskFixtures.ANY_UUID))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(TaskFixtures.ANY_HTTP_TASK_RESPONSE.id().toString())))
                .andExpect(jsonPath("$.name", is(TaskFixtures.ANY_HTTP_TASK_RESPONSE.name())))
                .andExpect(jsonPath("$.description", is(TaskFixtures.ANY_HTTP_TASK_RESPONSE.description())))
                .andExpect(jsonPath("$.priority", is(TaskFixtures.ANY_HTTP_TASK_RESPONSE.priority())));
    }

    @Test
    public void should_delete_a_not_found_given_an_uuid() throws Exception {
        Mockito.when(deleteTaskService.execute(ANY_DELETE_TASK_REQUEST)).thenThrow(new NoSuchElementException(""));
        mvc.perform(MockMvcRequestBuilders.delete("/v1/task/%s".formatted(TaskFixtures.ANY_UUID))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void should_retrieve_a_bad_request_given_an_id() throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete("/v1/task/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}
