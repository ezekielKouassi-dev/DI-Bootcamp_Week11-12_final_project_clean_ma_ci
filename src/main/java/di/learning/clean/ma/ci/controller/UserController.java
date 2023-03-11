package di.learning.clean.ma.ci.controller;

import di.learning.clean.ma.ci.entity.Assignment;
import di.learning.clean.ma.ci.entity.AssignmentUser;
import di.learning.clean.ma.ci.entity.AssignmentUserId;
import di.learning.clean.ma.ci.entity.User;
import di.learning.clean.ma.ci.error.AdherentNotFoundExceptionHandler;
import di.learning.clean.ma.ci.service.adherent_.UserService;
import di.learning.clean.ma.ci.service.assignment_.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
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

        // TODO : put into service user this instruction
        User user = userService.fetchUserById(userId);
        Assignment assignment = assignmentService.fetchAssignment(assignmentId);
        assignment.setNumberOfAcceptation(assignment.getNumberOfAcceptation() + 1);
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

    // TODO: leave assignment --->ok<---
    /*
         consist for the de user to revoke it he acceptation.
        my process to give leave assignment feature to the user is :
            - just change assignment status
     */
    @PatchMapping("/{userId}/assignments/{assignmentId}")
    public String leaveAssignment(@PathVariable Long userId, @PathVariable Long assignmentId) {
        return userService.leaveAssignment(userId, assignmentId);
    }

    // TODO : list of user assignment

    /*@GetMapping()
    public ResponseEntity<Page<?>> fetchAllAssignmentByState(
            Pageable pageable,
            @RequestParam(name = "sort", required = false) Sort sort) {
        return userService.fetchAllAssignmentByState(pageable, sort);
    }*/

}
