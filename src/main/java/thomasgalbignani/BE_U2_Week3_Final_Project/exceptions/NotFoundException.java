package thomasgalbignani.BE_U2_Week3_Final_Project.exceptions;

import java.util.UUID;

public class NotFoundException extends RuntimeException {
    public NotFoundException(UUID id){
        super("Element with id " + id + " not found!");
    }

    public NotFoundException(String message){
        super(message);
    }
}
