package com.tawandr.chain.of.responsibility.utils.example.transaction.configuration;

import com.tawandr.chain.of.responsibility.utils.ChainOfResponsibility;
import com.tawandr.chain.of.responsibility.utils.Chainable;
import com.tawandr.chain.of.responsibility.utils.example.transaction.dtos.Transaction;
import com.tawandr.chain.of.responsibility.utils.example.transaction.services.DiscountCalculator;
import com.tawandr.chain.of.responsibility.utils.example.transaction.services.TransactionChainBreaker;
import com.tawandr.chain.of.responsibility.utils.example.transaction.services.TransactionGenerator;
import com.tawandr.chain.of.responsibility.utils.example.transaction.services.TransactionPrinter;

/**
 * Created By Dougie T Muringani : 25/4/2020
 */
public class PurchaseTransactionProcessorService implements ChainOfResponsibility<Transaction> {
    @Override
    public Chainable<Transaction> buildChain() {
        return Chainable
                    .createChain(new TransactionGenerator())
                    .withNext(new TransactionPrinter())
                    .withNext(new DiscountCalculator(0.1))
                    .withNext(new TransactionChainBreaker())
                    .withNext(new TransactionPrinter());
    }
}
