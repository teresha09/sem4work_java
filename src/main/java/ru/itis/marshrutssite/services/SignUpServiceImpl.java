package ru.itis.marshrutssite.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.marshrutssite.dto.SignUpDto;
import ru.itis.marshrutssite.models.Role;
import ru.itis.marshrutssite.models.State;
import ru.itis.marshrutssite.models.User;
import ru.itis.marshrutssite.repositories.UsersRepository;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.ExecutorService;

@Service
public class SignUpServiceImpl implements SignUpService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private ExecutorService executorService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void signUp(SignUpDto signUpDto) {
        User user = User.builder()
                .email(signUpDto.getEmail())
                .password(signUpDto.getPassword())
                .hashPassword(passwordEncoder.encode(signUpDto.getPassword()))
                .name(signUpDto.getName())
                .createdAt(LocalDateTime.now())
                .state(State.NOT_CONFIRMED)
                .role(Role.USER)
                .confirmCode(UUID.randomUUID().toString())
                .build();

        usersRepository.save(user);

        executorService.submit(() ->
                emailService.sendMail("Confirm",
                        "Подтвердите Регистрацию нажав на ссылку, <br> http://localhost/confirm/"
                                + user.getConfirmCode(), user.getEmail()));

    }
}
