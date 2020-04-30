package com.tawandr.chain.of.responsibility.utils.example.number.services;

import com.tawandr.chain.of.responsibility.utils.Chainable;

/**
 * Created By Dougie T Muringani : 27/4/2020
 */
public class DividerService extends Chainable<Integer> {
    private final Integer divisor;

    public DividerService(final Integer divisor) {
        this.divisor = divisor;
    }

    @Override
    protected Integer processData(final Integer data) {
        System.out.printf("Dividing by %s...\n", divisor);
        final int result = data / divisor;
        System.out.println("result (int) = " + result);
        return result;
    }
}
