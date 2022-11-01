package backend.pretest.service;


import backend.pretest.entity.User;
import backend.pretest.exception.BaseBadRequestException;
import backend.pretest.exception.BaseInternalServerErrorException;
import backend.pretest.exception.BaseNotFoundException;
import backend.pretest.repository.UserRepository;
import lombok.SneakyThrows;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @SneakyThrows
    public User createUser(String username, String email, String password, String nickName) {
        existsUsername(username);
        User user = new User();
        user.setUserName(username.trim());
        user.setEmail(email.trim());
        user.setPassword(passwordEncoder.encode(password));
        user.setNickName(nickName.trim());

        return save(user);
    }

    @SneakyThrows
    public User login(String username, String password) {
        User user = findByUsername(username);
        boolean match = matchPassword(password, user.getPassword());
        if (!match) throw BaseBadRequestException.PasswordIncorrect();
        return user;
    }

    @SneakyThrows
    public User update(User user, String email, String nickName) {

        user.setEmail(email);
        user.setNickName(nickName);
        return save(user);
    }

    @SneakyThrows
    public User findByUsername(String username)  {
        Optional<User> opt = repository.findByUserName(username);
        if (opt.isEmpty()) throw BaseNotFoundException.NotFoundUser();
        return opt.get();
    }

    @SneakyThrows
    public void existsUsername(String username) {
        boolean exists = repository.existsByUserName(username);
        if (exists) throw BaseBadRequestException.UsernameDuplicate();
    }

    public boolean matchPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }


    @SneakyThrows
    public User changePassword(User user, String passwordOld, String passwordNew) {
        boolean match = matchPassword(passwordOld, user.getPassword());
        if (!match) throw BaseBadRequestException.PasswordIncorrect();
        user.setPassword(passwordEncoder.encode(passwordNew));
        user.setLast_password(new Date());
        return save(user);
    }

    @SneakyThrows
    private User save(User user) {
        try {
            return repository.save(user);
        } catch (Exception e) {
            throw BaseInternalServerErrorException.SaveError();
        }
    }
}
