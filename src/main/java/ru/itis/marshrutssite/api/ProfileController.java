package ru.itis.marshrutssite.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.marshrutssite.models.User;
import ru.itis.marshrutssite.services.UsersService;

import java.util.List;

@RestController
public class ProfileController {

    @Autowired
    private UsersService usersService;

    @GetMapping("/api/users")
    public ResponseEntity<List<User>> getUsers(){
        return ResponseEntity.ok(usersService.getAllUsers());
    }
}
