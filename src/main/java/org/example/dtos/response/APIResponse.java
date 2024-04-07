package org.example.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class APIResponse {
    boolean isSuccessful;
    Object RegistrationResponse;
}
