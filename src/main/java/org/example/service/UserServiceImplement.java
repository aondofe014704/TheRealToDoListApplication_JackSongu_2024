package org.example.service;

import org.example.data.model.Task;
import org.example.data.model.User;
import org.example.data.repository.TaskRepository;
import org.example.data.repository.UserRepository;
import org.example.dtos.requests.*;
import org.example.dtos.response.*;
import org.example.exceptions.InvalidPasswordException;
import org.example.exceptions.TaskNotFoundException;
import org.example.exceptions.UsernameNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImplement implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    public TaskRepository taskRepository;

    @Override
    public RegistrationResponse registerUser(RegisterUserRequest registerUserRequest) {
        User user = new User();
        user.setPassword(registerUserRequest.getPassword());
        user.setUsername(registerUserRequest.getUsername());
        user.setFirstname(registerUserRequest.getFirstname());
        user.setLastname(registerUserRequest.getLastname());
        userRepository.save(user);

        RegistrationResponse registrationResponse = new RegistrationResponse();
        registrationResponse.setFirstname(registerUserRequest.getFirstname());
        registrationResponse.setLastname(registerUserRequest.getLastname());
        registrationResponse.setUsername(registerUserRequest.getUsername());
        registrationResponse.setMessage("Registered Successfully");
        return registrationResponse;
    }

    @Override
    public String delete(CreateTaskRequest deleteTaskRequest) {
        Task foundTask = taskRepository.findByTitle(deleteTaskRequest.getTitle());
        if (foundTask != null){
            taskRepository.delete(foundTask);
            return "Task Successfully Deleted";
        }
        throw new TaskNotFoundException("Note not found");
    }

    @Override
    public LoginResponse login(LoginUserRequest loginUserRequest) {
        User user = findUserBy(loginUserRequest.getUsername());
        if (!(user.getPassword().equals(loginUserRequest.getPassword()))) throw new InvalidPasswordException("Password is incorrect");
        user.setLogIn(true);
        userRepository.save(user);

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setUsername(loginUserRequest.getUsername());
        loginResponse.setMessage("Login Successful");
        return loginResponse;
    }


    @Override
    public LogOutUserResponse logOut(LogoutUserRequest logoutUserRequest) {
        User user = findUserBy(logoutUserRequest.getUsername());
        user.setUsername(logoutUserRequest.getUsername());
        user.setLogIn(false);
        userRepository.save(user);
        LogOutUserResponse logOutUserResponse = new LogOutUserResponse();
        logOutUserResponse.setUsername(logoutUserRequest.getUsername());
        logOutUserResponse.setMessage("Successfully Logout.");
        return logOutUserResponse;
    }

    @Override
    public User findUserBy(String username) {
        User user = userRepository.findByUsername(username);
        if(user == null) throw new UsernameNotFoundException("Username Does Not Exist");
        return user;
    }

    @Override
    public long count() {
        return userRepository.count();
    }

    @Override
    public CreateTaskResponse createTask(CreateTaskRequest createTaskRequest) {
        User user = userRepository.findByUsername(createTaskRequest.getTaskOwner());
        Task task = new Task();
        task.setBody(createTaskRequest.getBody());
        task.setTitle(createTaskRequest.getTitle());
        task.setAuthor(createTaskRequest.getTaskOwner());
        taskRepository.save(task);
        if(user != null) {
            List<Task> taskList = user.getTasks();
            taskList.add(task);
            user.setTasks(taskList);
            userRepository.save(user);
        }
        CreateTaskResponse createTaskResponse = new CreateTaskResponse();
        createTaskResponse.setTitle(createTaskRequest.getTitle());
        createTaskResponse.setTaskOwner(createTaskRequest.getTaskOwner());
        createTaskResponse.setBody(createTaskRequest.getBody());
        createTaskResponse.setMessage("Task Created Successfully");

        return createTaskResponse;
    }

    @Override
    public UpdateTaskResponse update(UserUpdateTaskRequest userUpdateTaskRequest, CreateTaskRequest createTaskRequest) {
        User user = userRepository.findByUsername(createTaskRequest.getTaskOwner());
        Task task = taskRepository.findByTitle(createTaskRequest.getTitle());
        if(task != null) {
            task.setTitle(userUpdateTaskRequest.getNewTitle());
            task.setBody(userUpdateTaskRequest.getNewBody());
            task.setAuthor(userUpdateTaskRequest.getTaskOwner());
            taskRepository.save(task);
        }
        UpdateTaskResponse updateTaskResponse = new UpdateTaskResponse();
        updateTaskResponse.setMessage("Task Updated Successfully");
        return updateTaskResponse;
    }


}
