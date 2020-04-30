package com.tawandr.chain.of.responsibility.utils.example.transaction.services;

import com.tawandr.chain.of.responsibility.utils.Chainable;
import com.tawandr.chain.of.responsibility.utils.example.transaction.dtos.Transaction;

/**
 * Created By Dougie T Muringani : 25/4/2020
 * Example on How to break/exit chain execution
 */
public class TransactionChainBreaker extends Chainable<Transaction> {

    @Override
    protected Transaction processData(final Transaction data) {
        System.out.println("Validating Data...\nData:" + data);
        return chainBreaker(data,"sample validation failed");
    }

    @Override
    public String toString() {
        return "\n\tPrintTransaction{" +
                "\n\t\tnext=" + next +
                "\n\t}";
    }
}




