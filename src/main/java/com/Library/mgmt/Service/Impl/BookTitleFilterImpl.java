package com.Library.mgmt.Service.Impl;

import com.Library.mgmt.Enums.Operator;
import com.Library.mgmt.Model.Book;
import com.Library.mgmt.Repository.BookRepository;
import com.Library.mgmt.Service.BookFilterStratergy;
import com.Library.mgmt.dto.BookFilterResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookTitleFilterImpl implements BookFilterStratergy {

    @Autowired
    BookRepository bookRepository;

    @Override
    public List<BookFilterResponse> getFilteredBook(Operator operator, String value) {
        if (!operator.equals(Operator.EQUALS)){
            throw new IllegalArgumentException("Only Equals is expected with bookTitle");
        }

        List<Book> books = bookRepository.findByTitle(value);
        return books.
                stream().
                map(book -> convertToBookFilterResponse(book)).
                collect(Collectors.toList());
    }

    private BookFilterResponse convertToBookFilterResponse(Book book) {
        return BookFilterResponse.
                builder().
                bookName(book.getTitle()).
                bookType(book.getBookType()).
                bookNo(book.getBookNo()).
                authorName(book.getAuthor().getName()).
                authorEmail(book.getAuthor().getEmail()).
                build();
    }
}
