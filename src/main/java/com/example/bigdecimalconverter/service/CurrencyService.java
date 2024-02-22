package com.example.bigdecimalconverter.service;

import okhttp3.*;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;

@Service
public class CurrencyService {

    public BigDecimal getConversionExchangeRate(String currency, String targetCurrency) {

        OkHttpClient client = new OkHttpClient();

        String baseCurrencieValue = currency.replace("\"", "");
        String targetCurrencieValue = targetCurrency.replace("\"", "");

        System.out.println(baseCurrencieValue);
        System.out.println(targetCurrencieValue);

        HttpUrl.Builder urlBuilder = HttpUrl.parse("https://api.currencyapi.com/v3/latest").newBuilder();
        urlBuilder.addQueryParameter("apikey", "cur_live_dCaDfbidJ7WZWJCU7UzcPrYLQIL6iauPoXEbJ4ut");
        urlBuilder.addQueryParameter("base_currency", baseCurrencieValue);
        urlBuilder.addQueryParameter("currencies", targetCurrencieValue);
        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .build();

        try {
            Call call = client.newCall(request);
            Response response = call.execute();
            String responseBody = response.body().string();

            JSONObject jsonResponse = new JSONObject(responseBody);

            System.out.println(jsonResponse);

            BigDecimal exchangeRate = this.getExchangeRateFromJSONObject(jsonResponse, targetCurrencieValue);
            System.out.println(exchangeRate);
           return exchangeRate;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    private BigDecimal getExchangeRateFromJSONObject(JSONObject jsonObject, String targetCurrencyValue) {

            JSONObject data = jsonObject.getJSONObject("data");

            JSONObject targetCurrency = data.getJSONObject(targetCurrencyValue);

            return targetCurrency.getBigDecimal("value");
    }

    public BigDecimal convertAmount(String amount, BigDecimal exchangeRate) {
          String formattedAmount = amount.replace("\"", "");
          System.out.println(formattedAmount);
          BigDecimal amountDecimal = new BigDecimal(formattedAmount);

          return amountDecimal.multiply(exchangeRate);
    }
    public Boolean checkIfCurrenciesExists(String currency, String targetCurrency) {

        OkHttpClient client = new OkHttpClient();

        String currenciesValues = currency.replace("\"", "") + "," + targetCurrency.replace("\"", "");

        HttpUrl.Builder urlBuilder = HttpUrl.parse("https://api.currencyapi.com/v3/currencies").newBuilder();
        urlBuilder.addQueryParameter("apikey", "cur_live_dCaDfbidJ7WZWJCU7UzcPrYLQIL6iauPoXEbJ4ut");
        urlBuilder.addQueryParameter("currencies", currenciesValues);
        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .build();

        try {
            Call call = client.newCall(request);
            Response response = call.execute();

            if(response.isSuccessful()) {
                return true;
            } else {
                return  false;
            }

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
