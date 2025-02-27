package com.Library.mgmt.Service.Impl;

import com.Library.mgmt.Enums.BookFilter;
import com.Library.mgmt.Enums.Operator;
import com.Library.mgmt.Model.Author;
import com.Library.mgmt.Model.Book;
import com.Library.mgmt.Model.User;
import com.Library.mgmt.Repository.BookRepository;
import com.Library.mgmt.Service.BookFilterFactory;
import com.Library.mgmt.Service.BookFilterStratergy;
import com.Library.mgmt.dto.BookCreationRequest;
import com.Library.mgmt.dto.BookCreationResponse;
import com.Library.mgmt.dto.BookFilterResponse;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class BookService {

    @Autowired
    private BookRepository  bookRepository;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private BookFilterFactory bookFilterFactory;


    public BookCreationResponse addBook(BookCreationRequest request) {
        // To save book , we should add author as well. // So before adding booking , we should check wether that author already present or not, if he is present then we add to his list else add new book with new author
        Author authorFromDb = authorService.findAuthorInDb(request.getAuthorEmail());
        if (authorFromDb == null){
            authorFromDb = authorService.saveMyAuthor(Author.
                    builder().
                    id(UUID.randomUUID().toString()).
                    email(request.getAuthorEmail()).
                    name(request.getAuthorName()).
                    build());
        }
        Book book = request.toBook();
        book.setAuthor(authorFromDb);
//        book.setCreatedOn(new Date());
        book = bookRepository.save(book);
        return BookCreationResponse.
                builder().
                bookName(book.getTitle()).
                bookNo(book.getBookNo()).
                securityAmount(String.valueOf(book.getSecurityAmount())).
                build();
    }

    public List<BookFilterResponse> filter(@NotNull(message = "filterBy must not be null") BookFilter filterBy, @NotNull(message = "operator must not be null") Operator operator, @NotBlank(message = "value must not be blank") String value) {
        //Instead of using Switch statements or if else statements : we are using new and effective step
        BookFilterStratergy stratergy = bookFilterFactory.getStratergy(filterBy);
        return stratergy.getFilteredBook(operator,value);
    }

    public Book checkIfBookIsValid(@NotBlank(message = "bookNo must not be blank") String bookNo) {
//        return bookRepository.findBookByNo(bookNo);
       List<Book> books = bookRepository.findByBookNo(bookNo);
       if (books == null){
           return null;
       }
       return books.get(0);

    }

    public void markBookUnavailable(Book bookFromDb, User userFromDb) {
        bookFromDb.setUser(userFromDb);
        bookRepository.save(bookFromDb);
    }
}
