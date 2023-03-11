package di.learning.clean.ma.ci.controller;

import di.learning.clean.ma.ci.entity.Assignment;
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

    @PostMapping("/processingCompany/{id}")
    public String saveAssignment(@RequestBody Assignment assignment, @PathVariable("id") Long processingCompanyId) {
        return assignmentService.saveAssignment(assignment, processingCompanyId);
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


}
