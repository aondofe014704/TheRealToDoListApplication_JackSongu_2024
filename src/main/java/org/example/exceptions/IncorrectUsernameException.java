package org.example.exceptions;

public class IncorrectUsernameException extends TodoListException{
    public IncorrectUsernameException(String response){
        super(response);
    }
}
