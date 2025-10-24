package thomasgalbignani.BE_U2_Week3_Final_Project.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import thomasgalbignani.BE_U2_Week3_Final_Project.payloads.GeneralErrorDTO;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(EmailAlreadyUsedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)//400
    public GeneralErrorDTO handleEmailAlreadyUsedExc(EmailAlreadyUsedException ex) {
        return new GeneralErrorDTO(ex.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)//401
    public GeneralErrorDTO handleUnauthorizedExc(UnauthorizedException ex) {
        return new GeneralErrorDTO(ex.getMessage(), LocalDateTime.now());
    }
}