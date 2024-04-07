package org.example.service;
import org.example.data.model.Task;
import org.example.data.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImplement implements TaskService{
    @Autowired
    TaskRepository taskRepository;
    @Override
    public Task save(Task task) {
       return taskRepository.save(task);
    }

    @Override
    public List<Task> getAllTask() {
        return taskRepository.findAll();
    }

}
