package com.Library.mgmt.Repository;

import com.Library.mgmt.Model.Author;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@DataJpaTest - repository extends JPARepository, so this annotation helps with that, only for test
@DataJpaTest(properties = {
//       "spring.datasource.url=jdbc:mysql://localhost:3306/Library_Management?createDatabaseIfNotExist=true",
        "spring.datasource.url=jdbc:h2:mem:testdb",   // coz , we r using h2 DB
//        "spring.datasource.username=root",
//        "spring.datasource.password=rootroot",   //No need for h2 DB(in-memory DB)
        "spring.jpa.hibernate.ddl-auto=create-drop"}  // creates the data and drops after closing the application
)
public class AuthorRepositoryTest {

    @Autowired
    private AuthorRepository authorRepository;

    @BeforeEach
    public void setup(){
        Author a1 = Author.builder().email("a@gmail.com").id("1").name("a1").build();
        authorRepository.save(a1);

        Author a2= Author.builder().email("b@gmail.com").id("1").name("a2").build();
        authorRepository.save(a2);
    }

    @Test
    public void getFindByEmail(){
        Author author = authorRepository.findByEmail("a@gmail.com");
        assertEquals(author.getName(),"a1");
    }
}

// to check the repository , we need one database , that is in-memory database : H2 DB
//my test is going to be looking, return me the response
//we can use MySql also, but default we should use H2, coz , we don't want that data after testing

// 1). add the properties of h2 database
//2). Add the H2 DB dependency in pom.xml