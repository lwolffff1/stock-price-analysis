package stock_price_analyze.service;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import stock_price_analyze.client.StockApiClient;
import stock_price_analyze.client.StockJsonParser;
import stock_price_analyze.data.CsvReader;
import stock_price_analyze.exception.StockNotFoundException;
import stock_price_analyze.model.StockPrice;

@Service // File Service có nhiệm vụ lấy dữ liệu từ Csv Reader và trả về dữ liệu cho Controller
public class StockService {
    private final StockAnalyzer stockAnalyzer;
    private final CsvReader csvReader;
    private final StockApiClient stockApiClient;
    private final StockJsonParser stockJsonParser;

    public StockService(StockAnalyzer stockAnalyzer, CsvReader csvReader, StockApiClient stockApiClient) {
        this.stockAnalyzer = stockAnalyzer;
        this.csvReader = csvReader;
        this.stockApiClient = stockApiClient;
        this.stockJsonParser = new StockJsonParser(new tools.jackson.databind.json.JsonMapper());
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
    public List<StockPrice> getExternalStockPrices(String symbol) {
        String jsonData = stockApiClient.fetchDailyStockData(symbol);
        //1. Gọi StickApiClient để lấy dữ liệu JSON String từ API bên ngoài.
        //2. Gọi StockJsonParser để chuyển đổi dữ liệu JSON String thành danh sách các đối tượng StockPrice.
        return stockJsonParser.parseDailyStockPrices(jsonData);
    }
}
