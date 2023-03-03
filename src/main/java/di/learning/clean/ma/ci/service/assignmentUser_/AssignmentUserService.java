package di.learning.clean.ma.ci.service.assignmentUser_;

import di.learning.clean.ma.ci.entity.AssignmentUser;
import di.learning.clean.ma.ci.entity.AssignmentUserId;

public interface AssignmentUserService {

    public AssignmentUser fetchAssignmentUserById(AssignmentUserId assignmentUserId);
}
