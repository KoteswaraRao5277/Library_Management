package com.Library.mgmt.Model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

//@Getter
//@Setter           // I commented these for test cases for AuthorTest class. and created getter&setters manually
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Builder
@IdClass(AuthorCompositeKey.class)
public class Author extends TimeStamps{

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)    // this generated value is not work here, for composite key
//    private Integer id;   // so I have to generate Id by Myself
    @Id
    private String id;

    @Id
    @Column(nullable = false, unique = true, length = 50)
    private String email;

    @Column(length = 50)
    private String name;

    @OneToMany(mappedBy = "author")
    private List<Book> bookList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }
}

// I want to make a composite Key here