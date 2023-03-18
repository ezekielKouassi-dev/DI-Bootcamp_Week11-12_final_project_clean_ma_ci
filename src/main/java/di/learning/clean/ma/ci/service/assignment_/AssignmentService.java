package di.learning.clean.ma.ci.service.assignment_;

import di.learning.clean.ma.ci.entity.Assignment;
import di.learning.clean.ma.ci.error.AssignmentNotFoundExceptionHandler;
import di.learning.clean.ma.ci.model.AssignmentPayload;

import java.util.List;

public interface AssignmentService {
    public List<Assignment> fetchAssignments();

    public List<Assignment> fetchAllOrOneAssignment(int start, int limit, String search_value, List<Long> assignmentIds);
    public String fetchAvailableAssignments(Long userId, int start, int limit, String search_value);

    public int CountAllAvailableAssignments(int start, int limit, String search_value, List<Long> assignmentIds);

    public Assignment fetchAssignment(Long assignmentId);

    public String saveAssignment(AssignmentPayload assignmentPayload);

    public Assignment updateAssignment(Long assignmentId, Assignment assignment);

    public String fetchAllCollaborator(Long assignmentId);

}
