package thomasgalbignani.BE_U2_Week3_Final_Project.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import thomasgalbignani.BE_U2_Week3_Final_Project.entities.User;
import thomasgalbignani.BE_U2_Week3_Final_Project.exceptions.EmailAlreadyUsedException;
import thomasgalbignani.BE_U2_Week3_Final_Project.exceptions.NotFoundException;
import thomasgalbignani.BE_U2_Week3_Final_Project.payloads.NewUserDTO;
import thomasgalbignani.BE_U2_Week3_Final_Project.repositories.UserRepository;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder bcrypt;

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User findById(UUID id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("User with email " + email + " not found!"));
    }

    public User create(NewUserDTO newUser) {
        this.userRepository.findByEmail(newUser.email())
                .ifPresent(user -> {
                    throw new EmailAlreadyUsedException(user.getEmail());
                });

        User user = new User(newUser.username(), newUser.name(),
                newUser.surname(), newUser.email(), bcrypt.encode(newUser.password()));
        return userRepository.save(user);
    }
}
