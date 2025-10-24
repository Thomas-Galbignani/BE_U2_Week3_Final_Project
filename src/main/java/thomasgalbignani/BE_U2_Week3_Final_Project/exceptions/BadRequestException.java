package thomasgalbignani.BE_U2_Week3_Final_Project.exceptions;

import org.springframework.validation.ObjectError;

import java.util.List;

public class BadRequestException extends RuntimeException {
    private List<ObjectError> errorsList;

    public BadRequestException(String message){
        super(message);
    }

    public BadRequestException(List<ObjectError> errorsList){
        super("Errors in the body");
        this.errorsList = errorsList;
    }

    public List<ObjectError> getErrorsList() {
        return errorsList;
    }
}