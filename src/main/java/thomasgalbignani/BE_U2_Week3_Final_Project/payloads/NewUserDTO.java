package thomasgalbignani.BE_U2_Week3_Final_Project.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import thomasgalbignani.BE_U2_Week3_Final_Project.enums.Role;

public record NewUserDTO(@NotEmpty(message = "Username is required!!")
                         @Size(min = 3, max = 15, message = " your username must be  between 3 and 15 characters!")
                         String username,
                         @NotEmpty(message = "name is required!!")
                         @Size(min = 2, max = 15, message = " your name must be  between 2 and 15 characters!")
                         String name,
                         @NotEmpty(message = "Surname is required!")
                         @Size(min = 2, max = 15, message = " your surname must be  between 2 and 15 characters!")
                         String surname,
                         @NotEmpty(message = "email is required!")
                         @Email(message = "please check your email format!")
                         String email,
                         @NotEmpty(message = "password is required!")
                         String password,
                         @NotNull(message = "Role is required!!")
                         Role role) {

}
