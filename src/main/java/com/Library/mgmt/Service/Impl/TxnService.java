package com.Library.mgmt.Service.Impl;

import com.Library.mgmt.Enums.TxnStatus;
import com.Library.mgmt.Exception.BookException;
import com.Library.mgmt.Exception.TxnException;
import com.Library.mgmt.Exception.UserException;
import com.Library.mgmt.Model.Book;
import com.Library.mgmt.Model.Txn;
import com.Library.mgmt.Model.User;
import com.Library.mgmt.Repository.TxnRepository;
import com.Library.mgmt.dto.TxnRequest;
import com.Library.mgmt.dto.TxnReturnRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class TxnService {

    @Autowired
    private TxnRepository txnRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    @Value("${user.valid.days}")   //we gave this value in application.properties
    private int validUpTo;

    @Value("${user.delayed.finePerDay}")   //we gave this value in application.properties
    private int finePerDay;

    @Transactional(rollbackFor = {BookException.class, UserException.class})
    public String create(TxnRequest txnRequest) throws UserException, BookException {
        // whoever the user is trying to get the book, first check user is valid or not (Is he from our DB or not)
        User userFromDb = userService.checkIfUserIsValid(txnRequest.getUserEmail());
        if (userFromDb == null) {
            throw new UserException("User is not Valid . ");
        }

        //checking the Book No he is asking, it actually belongs to my library or not // what if the Book No he is passing doesn't exist in my library
        Book bookFromDb = bookService.checkIfBookIsValid(txnRequest.getBookNo());
        if (bookFromDb == null) {
            throw new BookException("Book is Not Valid . ");
        }

        //The book , a user is asking should not be assigned to another user? check weathe the book is assigned to any other user or not
        if (bookFromDb.getUser() != null) {
            throw new BookException("Book is not free to Issued . ");
        }
        return createTxn(userFromDb, bookFromDb);
    }

        //if above all the 3 checks are passed, then create a txn.
        @Transactional
        public String createTxn(User userFromDb, Book bookFromDb){
            String txnId = UUID.randomUUID().toString();
            Txn txn = Txn.
                    builder().
                    txnId(txnId).
                    user(userFromDb).
                    book(bookFromDb).
                    txnStatus(TxnStatus.ISSUED).
                    issuedDate(new Date()).
                    build();
            txnRepository.save(txn);
//        if (txn.getSettlementAmount()==null){
//            throw new BookException("exception has been thrown");
//        }
            bookService.markBookUnavailable(bookFromDb, userFromDb);
            return txnId;
        }

// @Transactional, propagateion (and its values like REQUIRED,SUPPORTS) , isolation (READ_COMMIT, READ_UNCOMMITTED)

    @Transactional(rollbackFor = {BookException.class, UserException.class})
    public Double returnTxn(TxnReturnRequest txnReturnRequest) throws UserException, BookException {
        // check wether user is valid or not
        User userFromDb = userService.checkIfUserIsValid(txnReturnRequest.getUserEmail());
        if (userFromDb == null) {
            throw new UserException("User is not Valid . ");
        }

        //check wether bookNo is valid or not
        Book bookFromDb = bookService.checkIfBookIsValid(txnReturnRequest.getBookNo());
        if (bookFromDb == null) {
            throw new BookException("Book is Not Valid . ");
        }
        if (bookFromDb.getUser() != null && bookFromDb.getUser().equals(userFromDb)){
            Txn txnFromDb = txnRepository.findByTxnId(txnReturnRequest.getTxnId());
            if (txnFromDb == null)
                throw new TxnException("No Txn is found in my DB with this txnId . ");

            Double amount = calculateSettlementAmount(txnFromDb, bookFromDb);

            if (amount == bookFromDb.getSecurityAmount())  //if calculateSettlementAmount returns same as getSecurityAmount, which means no delay or no fine
                txnFromDb.setTxnStatus(TxnStatus.RETURNED);
            else                                           // returned with fine amount
                txnFromDb.setTxnStatus(TxnStatus.FINED);
            txnFromDb.setSettlementAmount(amount);
            bookFromDb.setUser(null);                       // mark the book available
            txnRepository.save(txnFromDb);
            return amount;
        }else{
            throw new TxnException("Book is assigned to someone else OR not at all assigned");
        }
    }

//    private Double calculateSettlementAmount(Txn txnFromDb, Book bookFromDb) {   //changing this method private to public- just to write test cases for this. If we keep it as private , then the writing test case will become hectic, need to test all senarios in returnTxn method too before coming to this method. So to avoid all method testing , just change it from private to public
    public Double calculateSettlementAmount(Txn txnFromDb, Book bookFromDb) {
    long issueTime = txnFromDb.getIssuedDate().getTime();
        long returnTime = System.currentTimeMillis();
        long diff = returnTime - issueTime;
        int daysPassed = (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
        if (daysPassed > validUpTo){
            int fineAmount = (daysPassed - validUpTo) * finePerDay;
            return bookFromDb.getSecurityAmount() - fineAmount;
        }
        return bookFromDb.getSecurityAmount();
    }
}

