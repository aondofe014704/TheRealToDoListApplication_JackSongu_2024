package org.example.exceptions;

public class TodoListException extends RuntimeException {
    public TodoListException(String response){
        super(response);

    }

}
