package com.Library.mgmt.dto;

import com.Library.mgmt.Enums.BookType;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class BookFilterResponse {
    private String bookNo;
    private String bookName;
    private BookType bookType;
    private String authorName;
    private String authorEmail;

}
