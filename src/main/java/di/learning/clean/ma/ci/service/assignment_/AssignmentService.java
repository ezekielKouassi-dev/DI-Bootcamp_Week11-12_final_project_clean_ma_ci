package di.learning.clean.ma.ci.service.assignment_;

import di.learning.clean.ma.ci.entity.Assignment;
import di.learning.clean.ma.ci.error.AssignmentNotFoundExceptionHandler;

import java.util.List;

public interface AssignmentService {
    public List<Assignment> fetchAssignments();

    public Assignment fetchAssignment(Long assignmentId);

    public String saveAssignment(Assignment assignment);

    public Assignment updateAssignment(Long assignmentId, Assignment assignment);

    public String fetchAllCollaborator(Long assignmentId);
}
