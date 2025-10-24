package thomasgalbignani.BE_U2_Week3_Final_Project.exceptions;

public class AlreadyExistException extends RuntimeException {
    public AlreadyExistException(String message) {
        super(message);
    }
}
