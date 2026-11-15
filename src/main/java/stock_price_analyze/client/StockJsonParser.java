package stock_price_analyze.client;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;


import org.springframework.stereotype.Component;

import stock_price_analyze.model.StockPrice;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.json.JsonMapper;

@Component 
public class StockJsonParser {
    private final JsonMapper jsonMapper; 
    /*
    JsonMapper là đối tượng giúp đọc và chuyển đổi dữ liệu JSON
    -- JsonMapper là công cụ đọc Json --
    JsonNode là 1 nút trong cây JSON, có thể đại diện cho 1 object, array, chuỗi, số ... .
    -- Cấu trúc dữ liệu mà công cụ đó tạo ra để cta xử lý --
    
    */

    public StockJsonParser(JsonMapper jsonMapper) {
        this.jsonMapper = jsonMapper;
    }

    // Hàm này điều khiển quá trình chuyển đổi dữ liệu JSON thành danh sách các đối tượng StockPrice
    public List<StockPrice> parseDailyStockPrices(String json) {
        JsonNode root = jsonMapper.readTree(json);
        JsonNode timeSeriesNode = root.path("Time Series (Daily)");
        
        if(!timeSeriesNode.isObject()) {
            throw new IllegalArgumentException("Invalid JSON format: Time Series (Daily) not found");
        }

        
        List<StockPrice> stockPrices = new ArrayList<>();
        for (Map.Entry<String, JsonNode> entry : timeSeriesNode.properties()) {
            String date = entry.getKey();
            JsonNode dailyData = entry.getValue();
           
            JsonNode closeNode = dailyData.path("4. close");
            if(!closeNode.isString()) {
                throw new IllegalArgumentException("Missing or invalid closing price for date: " + date);
            }
            double close = Double.parseDouble(closeNode.asString());
            StockPrice stockPrice = new StockPrice(date, close);
            stockPrices.add(stockPrice);
        }
        stockPrices.sort(
            Comparator.comparing(StockPrice::getDate)
        );
        return stockPrices;
    }

}
