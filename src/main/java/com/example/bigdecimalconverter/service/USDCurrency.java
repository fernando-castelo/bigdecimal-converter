package com.example.bigdecimalconverter.service;

import com.example.bigdecimalconverter.interfaces.Currency;
import com.example.bigdecimalconverter.interfaces.CurrencyConverter;

public class USDCurrency implements Currency {
    @Override
    public String getCurrencyType() {
        return "USD";
    }

    @Override
    public CurrencyConverter getConverter() {
        return new USDConverter();
    }
}
