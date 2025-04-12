package services;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ExchangeRateClient {
    private final String apiKey = "37155c40a837cd1bd04ecb1e";
    private final String url = "https://v6.exchangerate-api.com/v6/"+apiKey;
    private final HttpClient httpClient = HttpClient.newHttpClient();

    public String getSupportedCodes() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(this.url+"/codes")).build();

        HttpResponse<String> response = this.httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    public String getCurrencyValues(String countryCodeBase, String countryCodeDestiny) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(this.url+"/pair/"+countryCodeBase+"/"+countryCodeDestiny)).build();

        HttpResponse<String> response = this.httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }
}
