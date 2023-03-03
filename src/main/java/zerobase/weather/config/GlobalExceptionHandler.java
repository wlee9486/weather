package zerobase.weather.config;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice // 전역 예외처리
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class) // 컨트롤러 안의 예외처리
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Exception handleAllException() {
        System.out.println("error from GlobalExceptionHandler");
        return new Exception();
    }
}
