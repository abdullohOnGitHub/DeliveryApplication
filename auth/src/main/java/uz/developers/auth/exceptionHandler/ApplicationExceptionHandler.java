package uz.developers.auth.exceptionHandler;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import uz.developers.auth.dto.ResponseDto;

import javax.validation.UnexpectedTypeException;

@ControllerAdvice
public class ApplicationExceptionHandler {
    @ExceptionHandler(value = {MethodArgumentNotValidException.class, InvalidFormatException.class, UnexpectedTypeException.class})
    public ResponseDto methodValidException(Exception e){
        return new ResponseDto(-1, e.getMessage(), null);
    }
}