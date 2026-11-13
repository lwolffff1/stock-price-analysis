package stock_price_analyze.controller;

import java.io.IOException;
import java.util.List;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import stock_price_analyze.model.StockPrice;
import stock_price_analyze.service.StockService;


@RestController

public class StockController {

    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping("/api/hello")
    public String hello() {
        return "Stock Price Analyzer API is running!";
    }
    @GetMapping("/api/stocks")
    public List<StockPrice> getAllStocks() throws IOException {
        return stockService.getAllStocks();
    }
    @GetMapping("/api/stocks/best-average")
    public double getBestAverage(@RequestParam int k) throws IOException {

        return stockService.getBestAverage(k);
    }
    @GetMapping ("/api/stocks/price-by-date")
    public double getPriceByDate(@RequestParam String date) throws IOException {
        return stockService.getPriceByDate( date);
    }

    @GetMapping("/api/stocks/average")
    public double getAverage(@RequestParam int k) throws IOException {
        return stockService.getAverage(k);
    }
    @GetMapping ("/api/stocks/search-date")
    public int getStockByDate(@RequestParam String date) throws IOException {
        return stockService.binarySearchDate(date);
    }
    @GetMapping ("/api/stocks/next-higher-price")
    public int[] getNextHigherPrice() throws IOException {
        return stockService.getNextHigherPrice();
    }
    @GetMapping("/api/stocks/external")
    public String getExternalStock(@RequestParam String symbol) {
        return stockService.getLiveStockData(symbol);
    }
}
