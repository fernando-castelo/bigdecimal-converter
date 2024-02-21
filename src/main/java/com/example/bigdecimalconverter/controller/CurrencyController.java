package com.example.bigdecimalconverter.controller;

import com.example.bigdecimalconverter.service.CurrencyService;
import okhttp3.*;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.math.BigDecimal;

@Controller
@RequestMapping("currencies")
public class CurrencyController {

    private final CurrencyService currencyService;

    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }


    @GetMapping("/convert")
    public ResponseEntity<String> convertAmounts(
            @RequestParam("amount") String amount,
            @RequestParam("currency") String currency,
            @RequestParam("targetCurrency") String targetCurrency
    ) {

        boolean checkCurrencyExists = currencyService.checkIfCurrenciesExists(currency, targetCurrency);

        if (checkCurrencyExists) {

            BigDecimal exchangeRate = currencyService.getConversionExchangeRate(currency, targetCurrency);

            BigDecimal convertedAmount = currencyService.convertAmount(amount, exchangeRate);

            String formattedAmount = amount.replace("\"", "");
            BigDecimal amountDecimal = new BigDecimal(formattedAmount);

            return ResponseEntity.ok().body( String.format("Montante em %s: %.2f%nMontante em %s: %.2f", currency, amountDecimal, targetCurrency, convertedAmount));

        }
        return ResponseEntity.ok().body("" + false);
    }
    @GetMapping("")
    public ResponseEntity<String> getCurrencies() {
        OkHttpClient client = new OkHttpClient();

        HttpUrl.Builder urlBuilder = HttpUrl.parse("https://api.currencyapi.com/v3/currencies").newBuilder();
        urlBuilder.addQueryParameter("apikey", "cur_live_dCaDfbidJ7WZWJCU7UzcPrYLQIL6iauPoXEbJ4ut");
        urlBuilder.addQueryParameter("currencies", "BRL");
        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .build();

        try {
            Call call = client.newCall(request);
            Response response = call.execute();
            String responseBody = response.body().string();

            JSONObject jsonResponse = new JSONObject(responseBody);

            return ResponseEntity.ok().body(responseBody);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred: " + e.getMessage());
        }
    }


}
