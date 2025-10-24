package thomasgalbignani.BE_U2_Week3_Final_Project.exceptions;

public class EmailAlreadyUsedException extends RuntimeException {
    public EmailAlreadyUsedException(String email) {
        super("The email " + email + " is already used!");
    }
}
