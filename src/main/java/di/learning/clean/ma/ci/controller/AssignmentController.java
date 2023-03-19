package di.learning.clean.ma.ci.controller;

import di.learning.clean.ma.ci.entity.Assignment;
import di.learning.clean.ma.ci.model.AssignmentPayload;
import di.learning.clean.ma.ci.service.assignment_.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/assignments")
@CrossOrigin(origins = "http://localhost:4200/")
public class AssignmentController {
    @Autowired
    private AssignmentService assignmentService;
    @GetMapping()
    public List<Assignment> fetchAssignments() {
        return assignmentService.fetchAssignments();
    }

    @GetMapping("/{id}")
    public Assignment fetchAssignment(@PathVariable("id") Long assignmentId) {
        return assignmentService.fetchAssignment(assignmentId);
    }

    @PostMapping()
    public String saveAssignment(@RequestBody AssignmentPayload assignmentPayload) {
        return assignmentService.saveAssignment(assignmentPayload);
    }

    @PutMapping("/{id}")
    public Assignment updateAssignemnt(@PathVariable("id") Long assignmentId, @RequestBody Assignment assignment) {
        return assignmentService.updateAssignment(assignmentId, assignment);
    }


    /**
     * this endPoint is used for give all collaborator for a specific assignment
     *
     * @param assignmentId
     * @return jsonObject
     */
    @GetMapping("/collaboratorAssignment/{id}")
    public String fetchAllCollaborator(@PathVariable("id") Long assignmentId) {
        return assignmentService.fetchAllCollaborator(assignmentId);
    }

    @GetMapping("/available/user/{id}")
    public String fetchAvailableAssignments(@PathVariable("id") Long userId,
                                            @RequestParam(name = "start", defaultValue = "0", required = false) int start,
                                            @RequestParam(name = "length", defaultValue = "10", required = false) int limit,
                                            @RequestParam(name = "search", required = false) String search_value
    ) {
        return assignmentService.fetchAvailableAssignments(userId, start, limit, search_value);
    }

    @PatchMapping("/{id}")
    public String validateAssignment(@PathVariable("id") Long assignmentId) {
        return assignmentService.validateAssignment(assignmentId);
    }


}
