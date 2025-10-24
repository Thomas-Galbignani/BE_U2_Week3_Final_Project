package thomasgalbignani.BE_U2_Week3_Final_Project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import thomasgalbignani.BE_U2_Week3_Final_Project.entities.User;
import thomasgalbignani.BE_U2_Week3_Final_Project.exceptions.BadRequestException;
import thomasgalbignani.BE_U2_Week3_Final_Project.payloads.NewUserDTO;
import thomasgalbignani.BE_U2_Week3_Final_Project.payloads.UserLoginDTO;
import thomasgalbignani.BE_U2_Week3_Final_Project.payloads.UserLoginResponseDTO;
import thomasgalbignani.BE_U2_Week3_Final_Project.services.AuthService;
import thomasgalbignani.BE_U2_Week3_Final_Project.services.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;


    @PostMapping("/login")
    public UserLoginResponseDTO login(@RequestBody UserLoginDTO body) {
        return new UserLoginResponseDTO(this.authService.authenticateUsersAndGenerateToken(body));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public User saveEmployee(@RequestBody @Validated NewUserDTO body, BindingResult validation) {
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        }
        return userService.create(body);
    }

}