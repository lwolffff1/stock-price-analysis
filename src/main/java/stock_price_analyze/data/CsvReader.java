package stock_price_analyze.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import stock_price_analyze.model.StockPrice;

public class CsvReader {
    public List<StockPrice> readCsv() throws IOException{
        List<StockPrice> stockPrices = new ArrayList<>();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("stock_prices.csv");
        if (inputStream == null) {
            throw new IOException("File not found: stock_prices.csv");
        }
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
                    reader.readLine(); // Skip header line
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String date = parts[0].trim();
                    double price = Double.parseDouble(parts[1].trim());
                    stockPrices.add(new StockPrice(date, price));
                }
            }

        }

    return stockPrices;
    }

}
