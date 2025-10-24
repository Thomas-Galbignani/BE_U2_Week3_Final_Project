package thomasgalbignani.BE_U2_Week3_Final_Project.payloads;

import jakarta.validation.constraints.NotEmpty;

public record UserLoginDTO(@NotEmpty(message = "Email is required! ")
                           String email,
                           @NotEmpty(message = "Password is required!")
                           String password) {
}