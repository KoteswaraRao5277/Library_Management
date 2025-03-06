package com.Library.mgmt.Repository;

import com.Library.mgmt.Model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest(properties = {
        "spring.datasource.url=jdbc:h2:mem:testdb",
        "spring.jpa.hibernate.ddl-auto=create-drop"}
)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void setup(){
        User user1= User.builder().name("koti").email("koti@gmail.com").build();
        userRepository.save(user1);

        User user2= User.builder().name("koti").email("koti2@gmail.com").build();
        userRepository.save(user2);
    }

    @Test
    public void testFindByName(){
        List<User> users = userRepository.findByName("koti");
        assertEquals(2,users.size());
    }
}
