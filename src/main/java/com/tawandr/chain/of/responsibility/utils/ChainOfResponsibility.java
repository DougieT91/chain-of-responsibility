package com.tawandr.chain.of.responsibility.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created By Dougie T Muringani : 25/4/2020
 */
public interface ChainOfResponsibility<T> {
    Logger log = LoggerFactory.getLogger(ChainOfResponsibility.class);

    Chainable<T> buildChain();

    default T process(T data) {
        final Chainable<T> chain = buildChain();
        log.info("Built Chain:\n{}\nProcessing Data Through Service Chain...", chain.printChain());
        final T processedData = chain.process(data);
        log.info("Chain Processing completed with result:\n{}\n\n", processedData);
        return processedData;
    }
}