package org.example.dtos.requests;

import lombok.Data;

@Data
public class CreateTaskRequest {
    private String title;
    private String body;
    private String taskOwner;
}
