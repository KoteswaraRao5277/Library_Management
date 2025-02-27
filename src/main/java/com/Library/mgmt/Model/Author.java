package com.Library.mgmt.Model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
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

}

// I want to make a composite Key here