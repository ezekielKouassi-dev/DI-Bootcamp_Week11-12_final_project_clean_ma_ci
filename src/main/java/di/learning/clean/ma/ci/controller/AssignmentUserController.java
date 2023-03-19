package di.learning.clean.ma.ci.controller;
import di.learning.clean.ma.ci.service.adherent_.UserService;
import di.learning.clean.ma.ci.service.assignmentUser_.AssignmentUserService;
import di.learning.clean.ma.ci.service.assignment_.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/assignmentUser")
@CrossOrigin(origins = "http://localhost:4200/")
public class AssignmentUserController {
    @Autowired
    private UserService userService;

    @Autowired
    private AssignmentService assignmentService;

    @Autowired
    private AssignmentUserService assignmentUserService;

    @GetMapping("/{id}/{state}")
    public String fetchAllUserAssignment(@PathVariable("id") Long userId, @PathVariable("state") String state) {
        return assignmentUserService.fetchAssignmentUserById(userId, state);
    }

}
