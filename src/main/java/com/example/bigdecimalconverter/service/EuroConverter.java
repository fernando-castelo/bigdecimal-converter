package com.example.bigdecimalconverter.service;

import com.example.bigdecimalconverter.interfaces.Currency;
import com.example.bigdecimalconverter.interfaces.CurrencyConverter;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Service
public class EuroConverter implements CurrencyConverter {

    private static final Map<Currency, BigDecimal> CONVERSION_RATES;

    static {
        CONVERSION_RATES = new HashMap<>();
        CONVERSION_RATES.put(new USDCurrency(), new BigDecimal("1.12"));
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
