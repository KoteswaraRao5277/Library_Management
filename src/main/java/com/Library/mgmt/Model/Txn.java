package com.Library.mgmt.Model;

import com.Library.mgmt.Enums.TxnStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Builder
public class Txn extends TimeStamps{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String txnId;

    @Enumerated
    private TxnStatus txnStatus;

    private Double settlementAmount;  //depending upon when your returning the book

    private Date issuedDate;

    private Date submittedDate;

    @ManyToOne
    @JoinColumn
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private Book book;

}
