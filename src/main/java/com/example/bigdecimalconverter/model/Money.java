package com.example.bigdecimalconverter.model;

import com.example.bigdecimalconverter.interfaces.Currency;
import com.example.bigdecimalconverter.interfaces.CurrencyConverter;
import com.example.bigdecimalconverter.service.EuroCurrency;
import com.example.bigdecimalconverter.service.USDCurrency;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Money {

    private BigDecimal amount;
    private Currency currency;

    public Money(BigDecimal amount, Currency currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "Money{" +
                "amount=" + amount +
                ", currency=" + currency +
                '}';
    }

    public Map<Currency, BigDecimal> getAvailableConversions() {
        CurrencyConverter converter = currency.getConverter();
        Map<Currency, BigDecimal> conversions = converter.getConversionRates();
        conversions.remove(this.currency);
        return conversions;
    }
}
