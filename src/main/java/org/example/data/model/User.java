package org.example.data.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document("User")
public class User {
    @Id
    private String username;
    private String firstname;
    private String lastname;
    private String password;
    private boolean isLogIn;
    private List<Task> tasks = new ArrayList<>();
}
