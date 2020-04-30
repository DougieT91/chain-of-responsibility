package com.tawandr.chain.of.responsibility.utils.example;


import com.tawandr.chain.of.responsibility.utils.ChainOfResponsibility;
import com.tawandr.chain.of.responsibility.utils.example.number.configuration.IntegerProcessorServiceAMD;
import com.tawandr.chain.of.responsibility.utils.example.number.configuration.IntegerProcessorServiceDAM;
import com.tawandr.chain.of.responsibility.utils.example.transaction.configuration.PurchaseTransactionProcessorService;
import com.tawandr.chain.of.responsibility.utils.example.transaction.dtos.Transaction;

/**
 * Demo Class used to demonstrate usage of the ChainOfResponsibility object
*/
class Main {
    /**
     * The following line can be done as a bean configuration if you are using Spring or a similar framework
     * The idea is just to get an instance of a ChainOfResponsibility object and use it to process some data
     * To do this, simply call the ChainOfResponsibility.process() method.
     */
    final static ChainOfResponsibility<Transaction> purchaseTransactionChain = new PurchaseTransactionProcessorService();
    final static ChainOfResponsibility<Integer> integerProcessorServiceAmd = new IntegerProcessorServiceAMD();
    final static ChainOfResponsibility<Integer> integerProcessorServiceDam = new IntegerProcessorServiceDAM();

    public static void main(String[] args) {
//        purchaseTransactionChain.process(null);
        integerProcessorServiceAmd.process(5);
        integerProcessorServiceDam.process(5);
        integerProcessorServiceAmd.process(5);
    }
}