package di.learning.clean.ma.ci.controller;

import di.learning.clean.ma.ci.entity.User;
import di.learning.clean.ma.ci.error.AdherentNotFoundExceptionHandler;
import di.learning.clean.ma.ci.service.adherent_.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping()
    public List<User> fetchAllUser() {
        return userService.fetchAllUser();
    }

    @GetMapping("/{id}")
    public User fetchUserById(@PathVariable("id") Long userId) throws AdherentNotFoundExceptionHandler {
        return userService.fetchUserById(userId);
    }

    @PostMapping()
    public String saveAdherent(@RequestBody User User) {
        return userService.saveUser(User);
    }

    @PutMapping("/{id}")
    public User updateAdherent(@PathVariable("id") Long userId, @RequestBody User User) {
        return userService.updateUser(userId, User);
    }

    @DeleteMapping("/{id}")
    public String deleteAdherentById(@PathVariable("id") Long userId) {
        return userService.deleteUserById(userId);
    }
}
