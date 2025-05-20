package services;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.CurrencyValuesResponseDto;
import dtos.SupportedCodesResponseDto;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataExtractor {

    private final ExchangeRateClient client = new ExchangeRateClient();
    private final Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).setPrettyPrinting().create();

    public Map<String, String> getSupportedCodesFromFile(){
        String filePath = "generated-files/currencies-code.txt";
        Map<String, String> currencies = new HashMap<>();

        try(BufferedReader bf = new BufferedReader(new FileReader(filePath))){
            String line;
            bf.readLine();
            bf.readLine();

            while((line = bf.readLine()) != null){
                String[] parts = line.split("\\s+");
                String code = parts[0];
                String name = parts[1];
                currencies.put(code, name);
            }

        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        return currencies;
    }

    public Map<String, String> getSupportedCodesFromClient(){
        try {
            String supportedCodes = client.getSupportedCodes();
            SupportedCodesResponseDto supportedCodesResponse = gson.fromJson(supportedCodes, SupportedCodesResponseDto.class);
            return getCurrencyResponseList(supportedCodesResponse);

        } catch (IOException | InterruptedException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    private Map<String, String> getCurrencyResponseList(SupportedCodesResponseDto supportedCodesResponse){
        Map<String, String> currencies = new HashMap<>();
        for(List<String> i : supportedCodesResponse.supportedCodes()){
            String code = i.get(0);
            String name = i.get(1);
            currencies.put(code, name);
        }
        return currencies;
    }

    public double getConversionRate(String countryCodeBase, String countryCodeDestiny){
        try {
            String values = client.getCurrencyValues(countryCodeBase, countryCodeDestiny);
            CurrencyValuesResponseDto conversionRateResponse = gson.fromJson(values, CurrencyValuesResponseDto.class);
            return conversionRateResponse.conversionRate();

        } catch (IOException | InterruptedException e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }
}
