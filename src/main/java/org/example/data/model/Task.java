package org.example.data.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;


@Data
@Document("Task")
public class Task {
        @Id
        private String title;
        private String body;
        private LocalDateTime dueDate=LocalDateTime.now();
        private String author;
        private boolean isCompleted;
}
