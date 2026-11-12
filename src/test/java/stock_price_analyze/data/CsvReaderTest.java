package stock_price_analyze.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import stock_price_analyze.model.StockPrice;

public class CsvReaderTest {
    void tesstReadCsv() {
        CsvReader csvReader = new CsvReader();
        try {
            csvReader.readCsv();
            List<StockPrice> stockPrices = csvReader.readCsv();
            assertNotNull(stockPrices);
            assertEquals(10, stockPrices.size()); // Assuming the CSV file has 10 entries
            assertEquals("2026-01-01", stockPrices.get(0).getDate());
            assertEquals(100.0, stockPrices.get(0).getPrice());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
