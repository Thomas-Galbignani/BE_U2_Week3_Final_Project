package thomasgalbignani.BE_U2_Week3_Final_Project.exceptions;

public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(String message) {
        super(message);
    }
}