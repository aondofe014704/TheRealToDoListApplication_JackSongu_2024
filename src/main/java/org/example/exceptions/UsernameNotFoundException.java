package org.example.exceptions;

public class UsernameNotFoundException extends TodoListException {
    public UsernameNotFoundException(String response) {
        super(response);
    }
}
