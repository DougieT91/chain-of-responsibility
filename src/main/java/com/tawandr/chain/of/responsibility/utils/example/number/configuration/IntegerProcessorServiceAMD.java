package com.tawandr.chain.of.responsibility.utils.example.number.configuration;

import com.tawandr.chain.of.responsibility.utils.ChainOfResponsibility;
import com.tawandr.chain.of.responsibility.utils.Chainable;
import com.tawandr.chain.of.responsibility.utils.example.number.services.AdderService;
import com.tawandr.chain.of.responsibility.utils.example.number.services.DividerService;
import com.tawandr.chain.of.responsibility.utils.example.number.services.IntegerValidatorService;
import com.tawandr.chain.of.responsibility.utils.example.number.services.MultiplierService;

/**
 * Created By Dougie T Muringani : 25/4/2020
 */
public class IntegerProcessorServiceAMD implements ChainOfResponsibility<Integer> {
    @Override
    public Chainable<Integer> buildChain() {
        return Chainable
                .createChain(new IntegerValidatorService())
                .withNext(new AdderService(5))
                .withNext(new MultiplierService(30))
                .withNext(new DividerService(15));
    }
}
