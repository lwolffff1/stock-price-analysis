package stock_price_analyze.service;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import stock_price_analyze.data.CsvReader;
import stock_price_analyze.exception.StockNotFoundException;
import stock_price_analyze.model.StockPrice;

@Service // File Service có nhiệm vụ lấy dữ liệu từ Csv Reader và trả về dữ liệu cho Controller
public class StockService {
    public List<StockPrice> getAllStocks() throws IOException {
        CsvReader csvReader = new CsvReader();
        return csvReader.readCsv();
    }
    public double getBestAverage(int k) throws IOException {

        List<StockPrice> stockPrices = getAllStocks();

        StockAnalyzer analyzer = new StockAnalyzer();

        return analyzer.bestAverage(stockPrices, k);
    }

    public double getPriceByDate(String date) throws IOException {
        StockAnalyzer analyzer = new StockAnalyzer();
        List<StockPrice> stockPrices = getAllStocks();
        try {
            return analyzer.findPriceByDate(stockPrices, date);

        } catch (IllegalArgumentException ex) {
            throw new StockNotFoundException(
                "Stock price not found for date: " + date
            );
        }
    }

}
