package stock_price_analyze.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component 
public class StockApiClient {
    private final RestClient restClient;
    private final String apiKey;
    public StockApiClient(@Value("${STOCK_API_KEY}") String apiKey) {
        this.restClient = RestClient.create(
            "https://www.alphavantage.co" );

        
        this.apiKey = apiKey;
    }

    public String fetchDailyStockData(String symbol) {
        return restClient.get()
        .uri(uriBuilder -> uriBuilder
                .path("/query")
                .queryParam("function", "TIME_SERIES_DAILY")
                .queryParam("symbol", symbol)
                .queryParam("apikey", apiKey)
                .build())
            .retrieve()
            .body(String.class);
    }
}
