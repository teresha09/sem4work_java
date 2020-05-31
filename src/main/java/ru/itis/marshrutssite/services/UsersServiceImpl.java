package ru.itis.marshrutssite.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import ru.itis.marshrutssite.models.User;
import ru.itis.marshrutssite.repositories.UsersRepository;


import java.util.List;
import java.util.Optional;

@Service
@PropertySource("classpath:application.properties")
public class UsersServiceImpl implements UsersService {

    private Environment environment;

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public List<User> getAllUsers() {
        return usersRepository.findAll();
    }

    @Override
    public Optional<User> check(String mail) {

        Optional<User> optionalUser = usersRepository.findUserByEmail(mail);
        if (optionalUser.isPresent()) {
            optionalUser.get();
            return optionalUser;
        } else {
            return Optional.empty();
        }

    }
}