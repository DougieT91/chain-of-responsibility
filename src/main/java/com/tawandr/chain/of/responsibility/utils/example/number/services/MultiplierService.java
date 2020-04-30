package com.tawandr.chain.of.responsibility.utils.example.number.services;

import com.tawandr.chain.of.responsibility.utils.Chainable;

/**
 * Created By Dougie T Muringani : 27/4/2020
 */
public class MultiplierService extends Chainable<Integer> {
    private final Integer multiple;

    public MultiplierService(final Integer multiple) {
        this.multiple = multiple;
    }

    @Override
    protected Integer processData(final Integer data) {
        System.out.printf("Multiplying by %s...\n", multiple);
        final int result = data * multiple;
        System.out.println("result = " + result);
        return result;
    }
}
