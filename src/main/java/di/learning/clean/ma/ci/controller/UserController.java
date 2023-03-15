package di.learning.clean.ma.ci.controller;

import di.learning.clean.ma.ci.entity.Adherent;
import di.learning.clean.ma.ci.error.AdherentNotFoundExceptionHandler;
import di.learning.clean.ma.ci.service.adherent_.UserService;
import di.learning.clean.ma.ci.service.assignment_.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin(origins = "http://localhost:4200/")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private AssignmentService assignmentService;
    @GetMapping()
    public List<Adherent> fetchAllUser() {
        return userService.fetchAllUser();
    }

    @GetMapping("/{id}")
    public Adherent fetchUserById(@PathVariable("id") Long userId) throws AdherentNotFoundExceptionHandler {
        return userService.fetchUserById(userId);
    }

    @PutMapping("/{id}")
    public Adherent updateAdherent(@PathVariable("id") Long userId, @RequestBody Adherent Adherent) {
        return userService.updateUser(userId, Adherent);
    }

    @DeleteMapping("/{id}")
    public String deleteAdherentById(@PathVariable("id") Long userId) {
        return userService.deleteUserById(userId);
    }

    @PostMapping("/{userId}/assignments/{assignmentId}")
    public String acceptAssignment(@PathVariable Long userId, @PathVariable Long assignmentId) {

        // TODO : put into service user this instruction
        return userService.saveUser(userId, assignmentId);
    }

    @PatchMapping("/{userId}/assignments/{assignmentId}")
    public String leaveAssignment(@PathVariable Long userId, @PathVariable Long assignmentId) {
        return userService.leaveAssignment(userId, assignmentId);
    }

    // TODO : list of user assignment

}
