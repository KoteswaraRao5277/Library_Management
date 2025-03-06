package com.Library.mgmt.Model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AuthorTest {
    // if i want to test my getter is working fine, I'll have to set the param first

    @Test
    public void testGetEmail(){
        //checking something here, return nothing - return type should be void
        Author author =new Author();
        author.setEmail("a@gmail.com");
        assertEquals("a@gmail.com", author.getEmail());
    }
}
