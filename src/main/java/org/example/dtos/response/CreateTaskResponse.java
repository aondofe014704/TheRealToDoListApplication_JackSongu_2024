package org.example.dtos.response;

import lombok.Data;

@Data
public class CreateTaskResponse {
    private String title;
    private String body;
    private String taskOwner;
    private String message;
}
