package com.tawandr.chain.of.responsibility.utils.example.number.services;

import com.tawandr.chain.of.responsibility.utils.Chainable;

/**
 * Created By Dougie T Muringani : 27/4/2020
 */
public class AdderService extends Chainable<Integer> {
    private final Integer addend;

    public AdderService(final Integer addend) {
        this.addend = addend;
    }

    @Override
    protected Integer processData(final Integer data) {
        System.out.printf("Adding by %s...\n", addend);
        final int result = data + addend;
        System.out.println("result = " + result);
        return result;
    }
}
