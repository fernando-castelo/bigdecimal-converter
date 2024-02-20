package com.example.bigdecimalconverter;

import com.example.bigdecimalconverter.interfaces.Currency;
import com.example.bigdecimalconverter.model.Money;
import com.example.bigdecimalconverter.service.EuroCurrency;
import com.example.bigdecimalconverter.service.RealCurrency;
import com.example.bigdecimalconverter.service.USDConverter;
import com.example.bigdecimalconverter.service.USDCurrency;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Scanner;

public class MAIN {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the amount to convert: ");
        String amountStr = scanner.nextLine();
        BigDecimal amount = new BigDecimal(amountStr);

        Money money = new Money(amount, new USDCurrency());

        Map<Currency, BigDecimal> conversions = money.getAvailableConversions();

        System.out.println("Choose the currency to convert to:");
        int index = 1;
        for (Currency currency : conversions.keySet()) {
            System.out.println(index + ". " + currency.getCurrencyType());
            index++;
        }

        int choice = scanner.nextInt();
        Currency targetCurrency = (Currency) conversions.keySet().toArray()[choice - 1];

        System.out.println("Converted amount: " + money.getCurrency().getConverter().convert(amount, targetCurrency));

        scanner.close();
    }
}
