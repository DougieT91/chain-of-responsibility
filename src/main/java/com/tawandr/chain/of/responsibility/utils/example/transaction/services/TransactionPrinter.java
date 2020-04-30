package com.tawandr.chain.of.responsibility.utils.example.transaction.services;

import com.tawandr.chain.of.responsibility.utils.Chainable;
import com.tawandr.chain.of.responsibility.utils.example.transaction.dtos.Transaction;

/**
 * Created By Dougie T Muringani : 24/4/2020
 */
public class TransactionPrinter extends Chainable<Transaction> {

    @Override
    protected Transaction processData(final Transaction data) {
        System.out.println("Printing Data...\nData:" + data);
        return data;
    }

    @Override
    public String toString() {
        return "\n\tPrintTransaction{" +
                "\n\t\tnext=" + next +
                "\n\t}";
    }
}




