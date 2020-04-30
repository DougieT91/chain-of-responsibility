package com.tawandr.chain.of.responsibility.utils.example.number.services;

import com.tawandr.chain.of.responsibility.utils.Chainable;

/**
 * Created By Dougie T Muringani : 27/4/2020
 */
public class IntegerValidatorService extends Chainable<Integer> {

    @Override
    protected Integer processData(final Integer data) {
        System.out.printf("Validating integer provided: [%s]%n", data);
        if (data == null) {
            System.out.println("[Validation FAILED]");
            return chainBreaker(data, "Null value Provided. Please provide a valid Integer value");
        }
        System.out.println("[Validation SUCCESSFUL]");
        return data;
    }
}
