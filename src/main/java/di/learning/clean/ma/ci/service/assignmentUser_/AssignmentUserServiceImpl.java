package di.learning.clean.ma.ci.service.assignmentUser_;

import di.learning.clean.ma.ci.entity.AssignmentUser;
import di.learning.clean.ma.ci.entity.AssignmentUserId;
import di.learning.clean.ma.ci.repository.AssignmentUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssignmentUserServiceImpl implements AssignmentUserService{
    @Autowired
    AssignmentUserRepository assignmentUserRepository;
    @Override
    public AssignmentUser fetchAssignmentUserById(AssignmentUserId assignmentUserId) {
        if(!assignmentUserRepository.findById(assignmentUserId).isPresent()) {
            // throw new exception ...
            return null;
        }
        return assignmentUserRepository.findById(assignmentUserId).get();
    }
}
