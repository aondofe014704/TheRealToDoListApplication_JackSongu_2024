package org.example.exceptions;

public class InvalidPasswordException extends TodoListException{
    public InvalidPasswordException(String response){
        super(response);

    }
}
