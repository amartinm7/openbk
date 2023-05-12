package com.amm.poc.openbk;

import com.amm.poc.openbk.application.task.service.CreateNewTaskRequest;
import com.amm.poc.openbk.domain.task.Task;
import com.amm.poc.openbk.domain.task.TaskDescriptionVO;
import com.amm.poc.openbk.domain.task.TaskNameVO;
import com.amm.poc.openbk.domain.task.TaskPriorityVO;
import com.amm.poc.openbk.infrastructure.task.controller.post.TaskHttpRequest;
import com.amm.poc.openbk.infrastructure.task.repository.JpaTask;

import java.util.UUID;

public class TaskFixtures {
    public static final String taskName = "task#1";
    public static final String taskDescription = "lorem ipsum";
    public static final int taskPriority = 3;
    public static final UUID ANY_UUID = UUID.fromString("3deab62e-2fe2-4f30-aede-96484fa3f738");
    public static final Task ANY_TASK = new Task(
            ANY_UUID,
            new TaskNameVO(taskName),
            new TaskDescriptionVO(taskDescription),
            new TaskPriorityVO(taskPriority)
    );
    public static final JpaTask ANY_JPA_TASK = new JpaTask(ANY_UUID, taskName, taskDescription, taskPriority);
    public static final CreateNewTaskRequest ANY_TASK_REQUEST = new CreateNewTaskRequest(taskName, taskDescription, taskPriority);
    public static final TaskHttpRequest HTTP_TASK_REQUEST = new TaskHttpRequest(taskName, taskDescription, taskPriority);
}
