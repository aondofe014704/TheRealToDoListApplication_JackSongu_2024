package org.example.controller;
import org.example.dtos.requests.*;
import org.example.dtos.response.*;
import org.example.exceptions.TodoListException;
import org.example.service.TaskService;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("api/v1")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private TaskService taskService;

    @PostMapping("/registerUser")
    public ResponseEntity<?> registerUser(@RequestBody RegisterUserRequest registerUserRequest){
        try {
            RegistrationResponse registrationResponse = userService.registerUser(registerUserRequest);
            return new ResponseEntity<>(new APIResponse(true, registrationResponse), CREATED);
        }catch (TodoListException r){
            return new ResponseEntity<>(new APIResponse(false, r.getMessage()), BAD_REQUEST);
        }

    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginUserRequest loginUserRequest) {
        try{
            LoginResponse loginResponse = userService.login(loginUserRequest);
            return new ResponseEntity<>(new APIResponse(true, loginResponse), CREATED);
        }
        catch (TodoListException r) {
            return new ResponseEntity<>(new APIResponse(false, r.getMessage()), BAD_REQUEST);
        }
    }
    @PostMapping("/logout")
    public ResponseEntity<?>logout(@RequestBody LogoutUserRequest logoutUserRequest){
        try{
            LogOutUserResponse logOutUserResponse = userService.logOut(logoutUserRequest);
            return new ResponseEntity<>(new APIResponse(true, logOutUserResponse), CREATED);
        }
        catch (TodoListException r){
            return new ResponseEntity<>(new APIResponse(false, r.getMessage()), BAD_REQUEST);
        }
    }

    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestBody UserUpdateTaskRequest userUpdateTaskRequest) {
        try{
            UpdateTaskResponse updateTaskResponse = userService.update(userUpdateTaskRequest, new CreateTaskRequest());
            return new ResponseEntity<>(new APIResponse(true, updateTaskResponse), CREATED);
        }
        catch (TodoListException r){
            return new ResponseEntity<>(new APIResponse(false, r.getMessage()), BAD_REQUEST);
        }
    }
    @PostMapping("/createTask")
    public ResponseEntity<?>createTask(@RequestBody CreateTaskRequest createTaskRequest){
        try {
            CreateTaskResponse createTaskResponse = userService.createTask(createTaskRequest);
            return new ResponseEntity<>(new APIResponse(true, createTaskResponse), CREATED);
        }
        catch (TodoListException r){
            return new ResponseEntity<>(new APIResponse(false, r.getMessage()), BAD_REQUEST);

        }
    }

    @PostMapping("/delete")
    public ResponseEntity<?>delete(@RequestBody CreateTaskRequest deleteTaskRequest){
        try {
            String result = userService.delete(deleteTaskRequest);
            return new ResponseEntity<>(new APIResponse(true, result), CREATED);
        }
        catch (TodoListException r){
            return new ResponseEntity<>(new APIResponse(false, r.getMessage()), BAD_REQUEST);

        }
    }
}
