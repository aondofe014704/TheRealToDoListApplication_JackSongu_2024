package org.example.service;

import org.example.data.model.User;
import org.example.dtos.requests.*;
import org.example.dtos.response.*;

public interface UserService {

    RegistrationResponse registerUser(RegisterUserRequest registerUserRequest);

    String delete(CreateTaskRequest deleteTaskRequest);

    LoginResponse login(LoginUserRequest loginUserRequest);

    LogOutUserResponse logOut(LogoutUserRequest logoutUserRequest);

    User findUserBy(String username);

    long count();

    CreateTaskResponse createTask(CreateTaskRequest createTaskRequest);

    UpdateTaskResponse update(UserUpdateTaskRequest userUpdateTaskRequest, CreateTaskRequest createTaskRequest);

}
