package com.tawandr.chain.of.responsibility.utils.example.transaction.dtos;

import java.time.LocalDateTime;

public class Transaction {
    private Long id;
    private String product;
    private Double price;
    private LocalDateTime dateOfPurchase;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public LocalDateTime getDateOfPurchase() {
        return dateOfPurchase;
    }

    public void setDateOfPurchase(LocalDateTime dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
    }

    @Override
    public String toString() {
        return "\n\tTransaction{" +
                " \n\t\tid=" + id +
                ", \n\t\tproduct='" + product + '\'' +
                ", \n\t\tprice=" + price +
                ", \n\t\tdateOfPurchase=" + dateOfPurchase +
                "\n\t}";
    }
}
