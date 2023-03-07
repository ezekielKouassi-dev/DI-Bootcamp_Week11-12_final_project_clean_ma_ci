package di.learning.clean.ma.ci.controller;

import di.learning.clean.ma.ci.model.UserPayload;
import di.learning.clean.ma.ci.service.adherent_.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    private UserService userService;
    @PostMapping("login/")
    public List<?> register(@RequestBody UserPayload userPayload) {
        return userService.register(userPayload);
    }
}
