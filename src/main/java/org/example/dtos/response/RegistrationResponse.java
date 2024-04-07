package org.example.dtos.response;

import lombok.Data;

@Data
public class RegistrationResponse {
    private String firstname;
    private String lastname;
    private String username;
    private String message;
}
