package thomasgalbignani.BE_U2_Week3_Final_Project.exceptions;

import lombok.Getter;
import org.springframework.validation.ObjectError;

import java.util.List;

@Getter
public class BadRequestException extends RuntimeException {
    private List<ObjectError> errorList;

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(List<ObjectError> errorList) {
        super("There were validation errors in the payload!");
        this.errorList = errorList;
    }
}
