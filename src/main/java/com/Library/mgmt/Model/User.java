package com.Library.mgmt.Model;


import com.Library.mgmt.Enums.UserStatus;
import com.Library.mgmt.Enums.UserType;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

//@Builder(toBuilder = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Builder
public class User extends TimeStamps{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 50)
    private String name;

    @Column(unique = true, length = 15)
    private String phoneNo;

    @Column(nullable = false, unique = true, length = 50)
    private String email;

    private String address;

    @Enumerated
    private UserStatus userStatus;

    @Enumerated
    private UserType userType;

    @Transient
    private String temp;

    @OneToMany(mappedBy = "user")
    private List<Book> bookList;

    @OneToMany(mappedBy = "user")
    private List<Txn> txnList;

}
