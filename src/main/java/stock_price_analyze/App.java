package stock_price_analyze;

import java.util.List;
import java.util.Scanner;

import stock_price_analyze.data.CsvReader;
import stock_price_analyze.model.StockPrice;
import stock_price_analyze.service.StockAnalyzer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * Hello world!
 *
 */
@SpringBootApplication 
public class App 
{

    public static void main(String[] args) {

        System.out.println("BEFORE SPRING BOOT");

        SpringApplication.run(App.class, args);

        System.out.println("AFTER SPRING BOOT");
    }
}
