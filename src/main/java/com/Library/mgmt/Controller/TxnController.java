package com.Library.mgmt.Controller;

import com.Library.mgmt.Exception.BookException;
import com.Library.mgmt.Exception.UserException;
import com.Library.mgmt.Service.Impl.TxnService;
import com.Library.mgmt.dto.TxnRequest;
import com.Library.mgmt.dto.TxnReturnRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/txn")
@Validated
public class TxnController {

    @Autowired
    private TxnService txnService;

//    @PostMapping("/issue")
//    public String create(@RequestBody TxnRequest txnRequest) throws UserException, BookException {
//        return txnService.create(txnRequest);
//    }

    @PostMapping("/issue")
    public ResponseEntity<String> create(@RequestBody @Validated TxnRequest txnRequest) throws UserException, BookException {
        String txnId = txnService.create(txnRequest);
        if (txnId != null || !txnId.isEmpty()){
            return new ResponseEntity<>(txnId, HttpStatus.OK);
        }
        return new ResponseEntity<>(txnId, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping("/return")
    public Double returnTxn(@RequestBody TxnReturnRequest txnReturnRequest) throws BookException, UserException {
        return txnService.returnTxn(txnReturnRequest);
    }
}

// Issue book, Return Book