package com.Library.mgmt.dto;

import com.Library.mgmt.Enums.BookType;
import com.Library.mgmt.Model.Book;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BookCreationRequest {

    @NotBlank(message = "Book Title must not be blank")
    private String bookTitle;

    @Positive(message = "positive values are expected")
    private String securityAmount;

    @NotBlank(message = "Book No must not be blank")
    private String bookNo;

    @NotNull(message= "Book Type must not be null")
    private BookType bookType;

    @NotBlank(message = "Author Name must not be blank")
    private String authorName;

    @NotBlank(message = "Author Email must not be blank")
    private String authorEmail;


    public Book toBook() {
        return Book.
                builder().
                title(this.bookTitle).
                securityAmount(Double.valueOf(this.securityAmount)).
                bookNo(this.bookNo).
                bookType(this.bookType).
                build();
    }
}
