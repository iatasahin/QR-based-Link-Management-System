package se360.shortlinker.restserver.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se360.shortlinker.restserver.model.User;
import se360.shortlinker.restserver.model.UserCredentialsDTO;
import se360.shortlinker.restserver.service.LinkService;
import se360.shortlinker.restserver.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserController {
    private final UserService userService;
    private final LinkService linkService;

    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/add")
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @DeleteMapping("/delete")
    public void deleteUser(@RequestBody User user) {
        userService.deleteUser(user);
    }

    @PatchMapping("/update")
    public User updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @PostMapping("/details")
    public ResponseEntity<User> loginGetUser(@RequestBody UserCredentialsDTO uC){
        if (uC.getUsername() == null || uC.getUsername().length() < 4
                || uC.getPassword() == null || uC.getPassword().length() < 4)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        User user = userService.getUserByUsername(uC.getUsername());
        if (user == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        if (user.getPassword().equals(uC.getPassword()))
            return ResponseEntity.status(HttpStatus.OK).body(user);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }
}
