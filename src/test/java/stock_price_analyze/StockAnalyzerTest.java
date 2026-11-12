package stock_price_analyze;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import stock_price_analyze.model.StockPrice;
import stock_price_analyze.service.StockAnalyzer;

public class StockAnalyzerTest {
    @Test 
    void testFindPriceByDate() {
        StockAnalyzer analyzer = new StockAnalyzer();
        List<StockPrice> stockPrices = Arrays.asList(
            new StockPrice("2026-01-01", 100.0),
            new StockPrice("2026-01-02", 200.0),
            new StockPrice("2026-01-03", 300.0)
        );
        double price = analyzer.findPriceByDate(stockPrices, "2026-01-02");
        assertEquals(200.0, price);
    }

    @Test
    void testBinarySearchDate() {
        StockAnalyzer analyzer = new StockAnalyzer();
        List<StockPrice> stockPrices = Arrays.asList(
            new StockPrice("2026-01-01", 100.0),
            new StockPrice("2026-01-02", 200.0),
            new StockPrice("2026-01-03", 300.0)
        );
        int index = analyzer.binarySearchDate(stockPrices, "2026-01-02");
        assertEquals(1, index);
    }

    @Test
    void testBestAverage() {
        StockAnalyzer analyzer = new StockAnalyzer();
        List<StockPrice> stockPrices = Arrays.asList(
            new StockPrice("2026-01-01", 100.0),
            new StockPrice("2026-01-02", 200.0),
            new StockPrice("2026-01-03", 300.0),
            new StockPrice("2026-01-04", 400.0)
        );
        double bestAvg = analyzer.bestAverage(stockPrices, 2);
        assertEquals(350.0, bestAvg);
    }

    @Test
    void testBestAverageInvalidK() {
        StockAnalyzer analyzer = new StockAnalyzer();
        List<StockPrice> stockPrices = Arrays.asList(
            new StockPrice("2026-01-01", 100.0),
            new StockPrice("2026-01-02", 200.0)
        );
        assertThrows(IllegalArgumentException.class, () -> {
            analyzer.bestAverage(stockPrices, 3);
        });
    }

    @Test
    void testFindPriceByDateInvalidDate() {
        StockAnalyzer analyzer = new StockAnalyzer();
        List<StockPrice> stockPrices = Arrays.asList(
            new StockPrice("2026-01-01", 100.0),
            new StockPrice("2026-01-02", 200.0)
        );
        assertThrows(IllegalArgumentException.class, () -> {
            analyzer.findPriceByDate(stockPrices, "2026-01-03");
        });
    }

    @Test
    void testBinarySearchDateInvalidDate() {
        StockAnalyzer analyzer = new StockAnalyzer();
        List<StockPrice> stockPrices = Arrays.asList(
            new StockPrice("2026-01-01", 100.0),
            new StockPrice("2026-01-02", 200.0)
        );
       assertThrows(IllegalArgumentException.class, () -> {
            analyzer.binarySearchDate(stockPrices, "2026-01-03");
        });
    }

    @Test
    void testBestAverageKZero() {
        StockAnalyzer analyzer = new StockAnalyzer();
        List<StockPrice> stockPrices = Arrays.asList(
            new StockPrice("2026-01-01", 100.0),
            new StockPrice("2026-01-02", 200.0)
        );
        assertThrows(IllegalArgumentException.class, () -> {
            analyzer.bestAverage(stockPrices, 0);
        });
    }

    @Test
    void testBestAverageKNegative() {
        StockAnalyzer analyzer = new StockAnalyzer();
        List<StockPrice> stockPrices = Arrays.asList(
            new StockPrice("2026-01-01", 100.0),
            new StockPrice("2026-01-02", 200.0)
        );
        assertThrows(IllegalArgumentException.class, () -> {
            analyzer.bestAverage(stockPrices, -1);
        });
    }
    
}
