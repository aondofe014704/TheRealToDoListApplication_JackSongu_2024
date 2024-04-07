package org.example.exceptions;

public class TaskNotFoundException extends TodoListException {
    public TaskNotFoundException(String noteNotFound) {
        super(noteNotFound);
    }
}
