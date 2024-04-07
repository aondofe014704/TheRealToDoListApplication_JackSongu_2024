package org.example.data.repository;

import org.example.data.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;
    @Test
    public void testToSaveAUserInTheRepository(){
        User user = new User();
        user.setFirstname("Jack");
        user.setLastname("Songu");
        user.setPassword("password");
        user.setUsername("JackSongu014704");
        userRepository.save(user);
        assertEquals(1, userRepository.count());

    }


}