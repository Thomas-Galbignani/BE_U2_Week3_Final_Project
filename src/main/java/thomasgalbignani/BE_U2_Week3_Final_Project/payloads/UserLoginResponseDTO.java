package thomasgalbignani.BE_U2_Week3_Final_Project.payloads;

import jakarta.validation.constraints.NotEmpty;

public record UserLoginResponseDTO(@NotEmpty(message = "Token is required!")
                                   String accessToken) {
}