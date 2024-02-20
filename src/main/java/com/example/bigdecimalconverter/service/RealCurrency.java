package com.example.bigdecimalconverter.service;

import com.example.bigdecimalconverter.interfaces.Currency;
import com.example.bigdecimalconverter.interfaces.CurrencyConverter;

public class RealCurrency implements Currency {
    @Override
    public String getCurrencyType() {
        return "BRL";
    }

    @Override
    public CurrencyConverter getConverter() {
        return new RealConverter();
    }
}
