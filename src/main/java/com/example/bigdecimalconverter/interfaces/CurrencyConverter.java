package com.example.bigdecimalconverter.interfaces;

import java.math.BigDecimal;
import java.util.Map;

public interface CurrencyConverter {

    BigDecimal convert(BigDecimal amount, Currency currency);

    Map<Currency, BigDecimal> getConversionRates();
}
