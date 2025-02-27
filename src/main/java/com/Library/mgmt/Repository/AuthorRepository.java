package com.Library.mgmt.Repository;

import com.Library.mgmt.Model.Author;
import com.Library.mgmt.Model.AuthorCompositeKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, AuthorCompositeKey> {

   Author findByEmail(String email);
}
