package com.Library.mgmt.Repository;

import com.Library.mgmt.Model.Txn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TxnRepository extends JpaRepository<Txn, Integer> {

    Txn findByTxnId(String txnId);
}
