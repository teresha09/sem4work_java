package ru.itis.marshrutssite.security.details;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.itis.marshrutssite.models.User;
import ru.itis.marshrutssite.repositories.UsersRepository;

import java.util.Optional;

@Service(value = "customUserDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> userOptional = usersRepository.findUserByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            UserDetailsImpl userDetails = new UserDetailsImpl(user);
            return userDetails;
        } throw new UsernameNotFoundException("User not found");
    }
}
