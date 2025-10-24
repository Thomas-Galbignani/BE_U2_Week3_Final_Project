package thomasgalbignani.BE_U2_Week3_Final_Project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import thomasgalbignani.BE_U2_Week3_Final_Project.entities.User;
import thomasgalbignani.BE_U2_Week3_Final_Project.exceptions.UnauthorizedException;
import thomasgalbignani.BE_U2_Week3_Final_Project.payloads.UserLoginDTO;
import thomasgalbignani.BE_U2_Week3_Final_Project.security.JWTTools;

@Service
public class AuthService {

    @Autowired
    private UserService us;

    @Autowired
    private PasswordEncoder bcrypt;

    @Autowired
    private JWTTools jt;

    public String authenticateUsersAndGenerateToken(UserLoginDTO body) {
        User user = this.us.findByEmail(body.email());
        if (bcrypt.matches(body.password(), user.getPassword())) {
            return jt.createToken(user.getId());

        } else {
            throw new UnauthorizedException("Invalid credentials! Please log in again!");
        }
    }
}