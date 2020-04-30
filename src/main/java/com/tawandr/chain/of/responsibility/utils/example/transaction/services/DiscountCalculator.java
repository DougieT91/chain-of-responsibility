package com.tawandr.chain.of.responsibility.utils.example.transaction.services;

import com.tawandr.chain.of.responsibility.utils.Chainable;
import com.tawandr.chain.of.responsibility.utils.example.transaction.dtos.Transaction;

public class DiscountCalculator extends Chainable<Transaction> {
    
    private final Double discountRate;

    public DiscountCalculator(final Double discountRate) {
        this.discountRate = discountRate;
    }

    @Override
    protected Transaction processData(final Transaction data) {
        System.out.println("Calculating Discount...");
        final Double price = data.getPrice();
        final Double discount = discountRate * price;

        final double discountedPrice = price - discount;
        data.setPrice(discountedPrice);

        System.out.printf("Discount awarded: $%s\nNew Price: $%s\n", discount, discountedPrice);
        return data;
    }

    @Override
    public String toString() {
        return "\n\tCalculateDiscount{" +
                "\n\t\tnext=" + next +
                "\n\t}";
    }
}
