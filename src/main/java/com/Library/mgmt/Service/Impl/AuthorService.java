package com.Library.mgmt.Service.Impl;

import com.Library.mgmt.Model.Author;
import com.Library.mgmt.Repository.AuthorRepository;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public Author findAuthorInDb(@NotBlank(message = "Author Email must not be blank") String authorEmail) {
        return authorRepository.findByEmail(authorEmail);
    }

    public Author saveMyAuthor(Author author) {
        return authorRepository.save(author);
    }

}
