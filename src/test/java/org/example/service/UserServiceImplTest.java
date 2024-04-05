package org.example.service;

import org.example.data.model.User;
import org.example.data.repository.UserRepository;
import org.example.dtos.LoginUserRequest;
import org.example.dtos.LogoutUserRequest;
import org.example.dtos.RegisterUserRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Test
    public void testToRegisterUser() {
        userRepository.deleteAll();
        RegisterUserRequest registerUserRequest = new RegisterUserRequest();
        registerUserRequest.setFirstname("Jack");
        registerUserRequest.setLastname("Songu");
        registerUserRequest.setPassword("password");
        registerUserRequest.setUsername("JackSongu014704");
        userService.registerUser(registerUserRequest);
        assertEquals(1, userRepository.count());
    }
    @Test
    public void testToRegisterTwoUsersAndDeleteOne(){
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
        userService.deleteByUserName("MaryClark0147041");
        assertEquals(1, userRepository.count());
    }
    @Test
    public void testLoginAUser(){
        userRepository.deleteAll();
        RegisterUserRequest registerUserRequest = new RegisterUserRequest();
        registerUserRequest.setLastname("BimBim");
        registerUserRequest.setUsername("MaryClark014704");
        registerUserRequest.setFirstname("Mary");
        registerUserRequest.setPassword("password");
        LoginUserRequest loginUserRequest = new LoginUserRequest();
        loginUserRequest.setUsername("MaryClark014704");
        loginUserRequest.setPassword("password");
        userService.login(loginUserRequest);
        assertEquals(1, userRepository.count());
    }
    @Test
    public void testToLoginTwoUsers(){
        userRepository.deleteAll();
        RegisterUserRequest registerUserRequest = new RegisterUserRequest();
        registerUserRequest.setLastname("BimBim");
        registerUserRequest.setUsername("MaryClark014704");
        registerUserRequest.setFirstname("Mary");
        registerUserRequest.setPassword("password");
        LoginUserRequest loginUserRequest = new LoginUserRequest();
        loginUserRequest.setUsername("MaryClark014704");
        loginUserRequest.setPassword("password");

        RegisterUserRequest registerUserRequest1 = new RegisterUserRequest();
        registerUserRequest1.setLastname("BimBim_Toheeb");
        registerUserRequest1.setUsername("MaryClark0147041");
        registerUserRequest1.setFirstname("Mary1");
        registerUserRequest1.setPassword("password1");
        LoginUserRequest loginUserRequest1 = new LoginUserRequest();
        loginUserRequest1.setPassword("password1");
        loginUserRequest1.setUsername("MaryClark0147041");

        userService.login(loginUserRequest);
        userService.login(loginUserRequest1);
        assertEquals(2, userRepository.count());
    }
    @Test
    public void testNotToRegisterWithSameUserName(){
        RegisterUserRequest registerUserRequest = new RegisterUserRequest();
        registerUserRequest.setUsername("MaryClark014704");
        registerUserRequest.setFirstname("Mary");
        registerUserRequest.setPassword("password");
        registerUserRequest.setLastname("Clark");
        LoginUserRequest loginUserRequest = new LoginUserRequest();
        loginUserRequest.setUsername("MaryClark014704");
        loginUserRequest.setPassword("password");
        userService.login(loginUserRequest);
        LogoutUserRequest logoutUserRequest = new LogoutUserRequest();
        logoutUserRequest.setUsername("MaryClark014704");
        userService.logOut(logoutUserRequest);
        assertEquals(0, userRepository.count());

    }
}