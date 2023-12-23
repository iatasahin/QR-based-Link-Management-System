package se360.shortlinker.restserver.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se360.shortlinker.restserver.model.User;
import se360.shortlinker.restserver.model.UserCredentialsDTO;
import se360.shortlinker.restserver.dao.UserDAO;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserController {
    private final UserDAO userDAO;

    @PostMapping("/add")
    public User addUser(@RequestBody UserCredentialsDTO uC) {
        return userDAO.saveUser(uC);
    }

    @PostMapping("/details")
    public ResponseEntity<User> loginGetUser(@RequestBody UserCredentialsDTO uC) {
        if (uC.getUsername() == null || uC.getUsername().length() < 4
                || uC.getPassword() == null || uC.getPassword().length() < 4)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        User user = userDAO.getUserByUsername(uC.getUsername());
        if (user == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        if (user.getPassword().equals(uC.getPassword()))
            return ResponseEntity.status(HttpStatus.OK).body(user);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }
}
