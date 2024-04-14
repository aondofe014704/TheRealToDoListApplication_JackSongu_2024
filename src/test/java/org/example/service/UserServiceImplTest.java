package org.example.service;

import org.example.data.model.User;
import org.example.data.repository.UserRepository;
import org.example.dtos.requests.*;
import org.example.dtos.response.RegistrationResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceImplTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private TaskService taskService;

    @Test
    public void testToRegisterUser() {
        userRepository.deleteAll();
        RegisterUserRequest registerUserRequest = new RegisterUserRequest();
        registerUserRequest.setFirstname("Jack");
        registerUserRequest.setLastname("Songu");
        registerUserRequest.setPassword("password");
        registerUserRequest.setUsername("JackSongu014704");
        RegistrationResponse response = userService.registerUser(registerUserRequest);
        assertEquals(1, userRepository.count());
    }

    @Test
    public void testToRegisterTwoUsers() {
        userRepository.deleteAll();
        RegisterUserRequest registerUserRequest = new RegisterUserRequest();
        RegisterUserRequest registerUserRequest1 = new RegisterUserRequest();
        registerUserRequest.setLastname("Clark");
        registerUserRequest.setFirstname("Mary");
        registerUserRequest.setPassword("Password");
        registerUserRequest.setUsername("MaryClark014704");

        registerUserRequest1.setLastname("Clark1");
        registerUserRequest1.setFirstname("Mary1");
        registerUserRequest1.setPassword("Password1");
        registerUserRequest1.setUsername("MaryClark0147041");
        userService.registerUser(registerUserRequest);
        userService.registerUser(registerUserRequest1);
        assertEquals(2, userRepository.count());
    }

    @Test
    public void testLoginAUser() {
        userRepository.deleteAll();
        RegisterUserRequest registerUserRequest = new RegisterUserRequest();
        registerUserRequest.setLastname("BimBim");
        registerUserRequest.setUsername("MaryClark014704");
        registerUserRequest.setFirstname("Mary");
        registerUserRequest.setPassword("password");
        userService.registerUser(registerUserRequest);

        LoginUserRequest loginUserRequest = new LoginUserRequest();
        loginUserRequest.setUsername("MaryClark014704");
        loginUserRequest.setPassword("password");
        userService.login(loginUserRequest);
        assertEquals(1, userRepository.count());
    }

    @Test
    public void testToLoginTwoUsers() {
        userRepository.deleteAll();
        RegisterUserRequest registerUserRequest1 = new RegisterUserRequest();
        registerUserRequest1.setLastname("BimBim");
        registerUserRequest1.setUsername("MaryClark014704");
        registerUserRequest1.setFirstname("Mary");
        registerUserRequest1.setPassword("password");
        userService.registerUser(registerUserRequest1);
        LoginUserRequest loginUserRequest1 = new LoginUserRequest();

        RegisterUserRequest registerUserRequest2 = new RegisterUserRequest();
        registerUserRequest2.setLastname("BimBim_Toheeb");
        registerUserRequest2.setUsername("MaryClark0147041");
        registerUserRequest2.setFirstname("Mary1");
        registerUserRequest2.setPassword("password1");
        userService.registerUser(registerUserRequest2);
        assertEquals(2, userService.count());

        LoginUserRequest loginUserRequest2 = new LoginUserRequest();
        loginUserRequest1.setPassword("password1");
        loginUserRequest1.setUsername("MaryClark0147041");
        loginUserRequest2.setUsername("MaryClark014704");
        loginUserRequest2.setPassword("password");

        userService.login(loginUserRequest2);
        userService.login(loginUserRequest1);
        User user1 = userService.findUserBy("MaryClark014704");
        User user2 = userService.findUserBy("MaryClark0147041");
    }
    @Test
    public void testNotToRegisterWithSameUserName() {
        userRepository.deleteAll();
        RegisterUserRequest registerUserRequest = new RegisterUserRequest();
        registerUserRequest.setUsername("MaryClark014704");
        registerUserRequest.setFirstname("Mary");
        registerUserRequest.setPassword("password");
        registerUserRequest.setLastname("Clark");
        userService.registerUser(registerUserRequest);
        assertEquals(1, userService.count());
        LoginUserRequest loginUserRequest = new LoginUserRequest();
        loginUserRequest.setUsername("MaryClark014704");
        loginUserRequest.setPassword("password");
        userService.login(loginUserRequest);
        User user = userService.findUserBy("MaryClark014704");
        assertTrue(user.isLogIn());
        LogoutUserRequest logoutUserRequest = new LogoutUserRequest();
        logoutUserRequest.setUsername("MaryClark014704");
        userService.logOut(logoutUserRequest);
        user = userService.findUserBy("MaryClark014704");
        assertFalse(user.isLogIn());
    }
    @Test
    public void testToCreateTask() {
        userRepository.deleteAll();
        RegisterUserRequest registerUserRequest = new RegisterUserRequest();
        registerUserRequest.setUsername("MaryClark014704");
        registerUserRequest.setFirstname("Mary");
        registerUserRequest.setPassword("password");
        registerUserRequest.setLastname("Clark");
        userService.registerUser(registerUserRequest);
        assertEquals(1, userService.count());

        LoginUserRequest loginUserRequest = new LoginUserRequest();
        loginUserRequest.setUsername("MaryClark014704");
        loginUserRequest.setPassword("password");
        userService.login(loginUserRequest);

        CreateTaskRequest createTaskRequest = new CreateTaskRequest();
        createTaskRequest.setTitle("Expression");
        createTaskRequest.setBody("The Ice Cream Integrated City");
        createTaskRequest.setTaskOwner("MaryClark014704");
        userService.createTask(createTaskRequest);
        assertEquals(1, userRepository.count());
    }
    @Test
    public void testToUpdateOrEditATask(){
        userRepository.deleteAll();
        RegisterUserRequest registerUserRequest = new RegisterUserRequest();
        registerUserRequest.setFirstname("Jack");
        registerUserRequest.setLastname("Mary");
        registerUserRequest.setPassword("password");
        registerUserRequest.setUsername("MaryClark014704");
        userService.registerUser(registerUserRequest);
        LoginUserRequest loginUserRequest = new LoginUserRequest();
        loginUserRequest.setUsername(registerUserRequest.getUsername());
        loginUserRequest.setPassword(registerUserRequest.getPassword());
        userService.login(loginUserRequest);
        User user = userService.findUserBy("MaryClark014704");
        assertTrue(user.isLogIn());
        CreateTaskRequest createTaskRequest = new CreateTaskRequest();
        createTaskRequest.setTitle("Crown Star");
        createTaskRequest.setBody("Express");
        createTaskRequest.setTaskOwner(registerUserRequest.getUsername());
        userService.createTask(createTaskRequest);
        UserUpdateTaskRequest userUpdateTaskRequest = new UserUpdateTaskRequest();
        userUpdateTaskRequest.setNewTitle("The Java Journey");
        userUpdateTaskRequest.setNewBody("You won't Appreciate My own experience, You have to have Yours");
        userUpdateTaskRequest.setTaskOwner(createTaskRequest.getTaskOwner());
        userService.update(userUpdateTaskRequest, createTaskRequest);

    }
    @Test
    public void registerUser_login_createTask_deleteTaskTest(){
        userRepository.deleteAll();
        RegisterUserRequest registerUserRequest = new RegisterUserRequest();
        registerUserRequest.setUsername("MaryClark014704");
        registerUserRequest.setFirstname("Mary");
        registerUserRequest.setPassword("password");
        registerUserRequest.setLastname("Clark");
        userService.registerUser(registerUserRequest);

        assertEquals(1, userService.count());
        LoginUserRequest loginUserRequest = new LoginUserRequest();
        loginUserRequest.setUsername("MaryClark014704");
        loginUserRequest.setPassword("password");
        userService.login(loginUserRequest);

        CreateTaskRequest createTaskRequest = new CreateTaskRequest();
        createTaskRequest.setTitle("Crown Star");
        createTaskRequest.setBody("Express");
        createTaskRequest.setTaskOwner("MaryClark014704");
        userService.createTask(createTaskRequest);
        userService.delete(createTaskRequest);
        assertEquals(0, taskService.getAllTask().size());
    }
}