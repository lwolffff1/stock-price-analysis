package stock_price_analyze.service;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import stock_price_analyze.client.StockApiClient;
import stock_price_analyze.data.CsvReader;
import stock_price_analyze.exception.StockNotFoundException;
import stock_price_analyze.model.StockPrice;

@Service // File Service có nhiệm vụ lấy dữ liệu từ Csv Reader và trả về dữ liệu cho Controller
public class StockService {
    private final StockAnalyzer stockAnalyzer;
    private final CsvReader csvReader;
    private final StockApiClient stockApiClient;

    public StockService(StockAnalyzer stockAnalyzer, CsvReader csvReader, StockApiClient stockApiClient) {
        this.stockAnalyzer = stockAnalyzer;
        this.csvReader = csvReader;
        this.stockApiClient = stockApiClient;
    }
    public List<StockPrice> getAllStocks() throws IOException {
        return csvReader.readCsv();
    }
    public double getBestAverage(int k) throws IOException {

        List<StockPrice> stockPrices = getAllStocks();
        return stockAnalyzer.bestAverage(stockPrices, k);
    }

    public double getPriceByDate(String date) throws IOException {
        List<StockPrice> stockPrices = getAllStocks();
        try {
            return stockAnalyzer.findPriceByDate(stockPrices, date);

        } catch (IllegalArgumentException ex) {
            throw new StockNotFoundException(
                "Stock price not found for date: " + date
            );
        }
    }

    public double getAverage(int k) throws IOException {
        List<StockPrice> stockPrices = getAllStocks();
        return stockAnalyzer.getAverage(stockPrices, k);
    }

    public int binarySearchDate(String targetDate) throws IOException {
        List<StockPrice> stockPrices = getAllStocks();
        
        return stockAnalyzer.binarySearchDate(stockPrices, targetDate);
    }
    public int[] getNextHigherPrice() throws IOException {
        List<StockPrice> stockPrices = getAllStocks();
        return stockAnalyzer.nextHigherPrice(stockPrices);
    }
    public String getLiveStockData(String symbol) {
        return stockApiClient.fetchDailyStockData(symbol);
    }
}
