package org.example.data.repository;

import org.example.data.model.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TaskRepositoryTest {
    @Autowired
    private TaskRepository taskRepository;
    @Test
    public void testToCreateATask(){
        Task task = new Task();
        task.setTitle("Be Cheerful");
        task.setBody("The Universe is Unfolding as it Should");
        taskRepository.save(task);
        assertEquals(1, taskRepository.count());


    }


}