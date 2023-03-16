package di.learning.clean.ma.ci.controller;

import di.learning.clean.ma.ci.model.UserPayload;
import di.learning.clean.ma.ci.service.adherent_.UserService;
import di.learning.clean.ma.ci.service.auth_.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "http://localhost:4200/")
public class AuthController {
    @Autowired
    private AuthService authService;
    @PostMapping("signUp")
    public String register(@RequestBody UserPayload userPayload) {
        return authService.register(userPayload);
    }

    @PostMapping("signIn")
    public String login(@RequestBody UserPayload userPayload) {
        return authService.login(userPayload);
    }
}
