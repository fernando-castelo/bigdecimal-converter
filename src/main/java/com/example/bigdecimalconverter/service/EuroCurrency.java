package com.example.bigdecimalconverter.service;

import com.example.bigdecimalconverter.interfaces.Currency;
import com.example.bigdecimalconverter.interfaces.CurrencyConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

public class EuroCurrency implements Currency {

    @Override
    public String getCurrencyType() {
        return "EUR";
    }

    @Override
    public CurrencyConverter getConverter() {
        return new EuroConverter();
    }
}
