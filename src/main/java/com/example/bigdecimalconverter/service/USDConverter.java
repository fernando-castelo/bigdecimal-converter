package com.example.bigdecimalconverter.service;

import com.example.bigdecimalconverter.interfaces.Currency;
import com.example.bigdecimalconverter.interfaces.CurrencyConverter;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class USDConverter implements CurrencyConverter {
    private static final Map<Currency, BigDecimal> CONVERSION_RATES;

    static {
        CONVERSION_RATES = new HashMap<>();
        CONVERSION_RATES.put(new EuroCurrency(), new BigDecimal("0.89"));
        CONVERSION_RATES.put(new RealCurrency(), new BigDecimal("0.20"));
    }
    @Override
    public BigDecimal convert(BigDecimal amount, Currency currency) {
        BigDecimal conversionRate = CONVERSION_RATES.get(currency);
        if(conversionRate == null) {
            throw new IllegalArgumentException("Conversion rate not found");
        }
        return amount.multiply(conversionRate);
    }

    @Override
    public Map<Currency, BigDecimal> getConversionRates() {
        return CONVERSION_RATES;
    }
}
