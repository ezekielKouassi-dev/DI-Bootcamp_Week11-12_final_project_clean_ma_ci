package di.learning.clean.ma.ci.controller;

import di.learning.clean.ma.ci.entity.Assignment;
import di.learning.clean.ma.ci.entity.AssignmentUser;
import di.learning.clean.ma.ci.entity.AssignmentUserId;
import di.learning.clean.ma.ci.entity.User;
import di.learning.clean.ma.ci.error.AdherentNotFoundExceptionHandler;
import di.learning.clean.ma.ci.service.adherent_.UserService;
import di.learning.clean.ma.ci.service.assignment_.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private AssignmentService assignmentService;
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

    @PostMapping("/{userId}/assignments/{assignmentId}")
    public String acceptAssignment(@PathVariable Long userId, @PathVariable Long assignmentId) {
        User user = userService.fetchUserById(userId);
        Assignment assignment = assignmentService.fetchAssignment(assignmentId);
        AssignmentUser assignmentUser = new AssignmentUser();
        AssignmentUserId assignmentUserId = new AssignmentUserId();
        assignmentUserId.setUserId(userId);
        assignmentUserId.setAssignmentId(assignmentId);
        assignmentUser.setId(assignmentUserId);
        assignmentUser.setUser(user);
        assignmentUser.setAssignment(assignment);
        user.getAssignmentUsers().add(assignmentUser);
        assignment.getAssignmentUsers().add(assignmentUser);

        return userService.saveUser(user);
    }
}
