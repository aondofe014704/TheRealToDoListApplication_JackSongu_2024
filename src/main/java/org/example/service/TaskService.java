package org.example.service;

import org.example.data.model.Task;

import java.util.Collection;
import java.util.List;

public interface TaskService {


    Task save(Task task);

    List<Task> getAllTask();
}
