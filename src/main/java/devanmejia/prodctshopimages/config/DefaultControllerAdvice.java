package devanmejia.prodctshopimages.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DefaultControllerAdvice {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleException(IllegalArgumentException e) {
        String responseMsg = e.getMessage();
        e.printStackTrace();
        return new ResponseEntity<>(responseMsg, HttpStatus.NOT_FOUND);
    }
}
