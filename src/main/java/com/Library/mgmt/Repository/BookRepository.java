package com.Library.mgmt.Repository;

import com.Library.mgmt.Enums.BookType;
import com.Library.mgmt.Model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    List<Book> findByBookNo(String bookNo);

    List<Book> findByTitle(String title);

    List<Book> findByBookType(BookType bookType);

//    Book findBookByNo(String bookNo);
}
