package org.example.dtos.requests;

import lombok.Data;

@Data
public class UserUpdateTaskRequest {
    private String newTitle;
    private String taskOwner;
    private String newBody;


}
