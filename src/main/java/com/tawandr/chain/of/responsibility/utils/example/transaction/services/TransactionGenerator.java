package com.tawandr.chain.of.responsibility.utils.example.transaction.services;


import com.tawandr.chain.of.responsibility.utils.Chainable;
import com.tawandr.chain.of.responsibility.utils.example.transaction.dtos.Transaction;

import java.time.LocalDateTime;

public class TransactionGenerator extends Chainable<Transaction> {
    @Override
    protected Transaction processData(final Transaction data) {

        Transaction transaction = null;
        if (data==null || (data.getId()==null || data.getId()==0L)) {
            System.out.println("Data object is null/empty");
            transaction = new Transaction();
            transaction.setId(1L);
            transaction.setProduct("Iphone X");
            transaction.setPrice(1000.00);
            transaction.setDateOfPurchase(LocalDateTime.now());
        }

        System.out.println("Populating Data...\nPopulated data: " + transaction);
        return transaction;
    }

    @Override
    public String toString() {
        return "\n\tPopulateTransaction{" +
                "\n\t\tnext=" + next +
                "\n\t}";
    }
}
