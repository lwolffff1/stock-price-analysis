package stock_price_analyze.client;

import java.io.IOException;
import java.util.List;

import org.springframework.aot.hint.TypeReference;
import org.springframework.stereotype.Component;

import stock_price_analyze.model.StockPrice;
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
        
    }

}
