package com.luckcheese.yuca.model;

public class Price {

    private String current;
    private String installment;

    public Price(String current, String installment) {
        this.current = current;
        this.installment = installment;
    }

    public String getCurrent() {
        return current;
    }

    public String getInstallment() {
        return installment;
    }
}
