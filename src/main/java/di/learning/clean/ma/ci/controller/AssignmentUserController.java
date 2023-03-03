package di.learning.clean.ma.ci.controller;

import di.learning.clean.ma.ci.entity.AssignmentUser;
import di.learning.clean.ma.ci.service.adherent_.UserService;
import di.learning.clean.ma.ci.service.assignmentUser_.AssignmentUserService;
import di.learning.clean.ma.ci.service.assignment_.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/assignmentUser")
public class AssignmentUserController {
    @Autowired
    private UserService userService;

    @Autowired
    private AssignmentService assignmentService;

    @Autowired
    private AssignmentUserService assignmentUserService;
    @PostMapping()
    public String saveAssignmentUser() {
        return "";
    }
}
