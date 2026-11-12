package stock_price_analyze.exception;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice // Xử lý Exception phát sinh từ các Controller trong ứng dụng
public class GlobalExceptionHandler {
    @ExceptionHandler(IllegalArgumentException.class)
    public ProblemDetail handleIllegalArgumentException(IllegalArgumentException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setDetail(ex.getMessage());
        return problemDetail;
    }

    @ExceptionHandler(IOException.class)
    public ProblemDetail handleIOException(IOException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        problemDetail.setDetail("An error occurred while processing the request.");
        return problemDetail;
    }
    @ExceptionHandler (NumberFormatException.class)
    public ProblemDetail handleNumberFormatException(NumberFormatException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        problemDetail.setDetail("Invalid number format.");
        return problemDetail;
    }
    @ExceptionHandler (StockNotFoundException.class)
    public ProblemDetail handleStockNotFoundException(StockNotFoundException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        problemDetail.setDetail(ex.getMessage());
        return problemDetail;
    }
    @ExceptionHandler (MethodArgumentTypeMismatchException.class)
    public ProblemDetail handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setDetail(
            "Invalid value for parameter: " + ex.getName()
        );
        return problemDetail;
    }
    @ExceptionHandler (MissingServletRequestParameterException.class)
    public ProblemDetail handleMissingServletRequestParameterException(MissingServletRequestParameterException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setDetail(
            "Missing required parameter: " + ex.getParameterName()
        );
        return problemDetail;
    }
}
