package com.Library.mgmt.Model;

import com.Library.mgmt.Enums.BookType;
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
public class Book extends TimeStamps{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 50)
    private String title;

    @Column(length = 20,unique = true)
    private String bookNo;

    @Enumerated
    private BookType bookType;

    @Column(nullable = false)
    private Double securityAmount;

    @ManyToOne  //many books can be present in one user
    @JoinColumn
    private User user;  //UNI-DIRECTIONAL => we can see users from book table(which book is allocated to which user), but we can't see books from user table

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumns({                          //creating new column in book table for author composite key //inside the book table, there 2 new columns author_id and author_email
            @JoinColumn(name = "author_id",referencedColumnName = "id"),
            @JoinColumn(name = "author_email", referencedColumnName = "email")
    })
    private Author author;

    @OneToMany(mappedBy = "book")
    private List<Txn> txnList;
}
