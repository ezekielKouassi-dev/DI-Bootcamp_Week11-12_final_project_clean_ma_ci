package di.learning.clean.ma.ci.service.assignmentUser_;

import di.learning.clean.ma.ci.entity.AssignmentUser;
import di.learning.clean.ma.ci.entity.AssignmentUserId;

public interface AssignmentUserService {

    public String fetchAssignmentUserById(Long userId, String state);

    public String fetchStatistcsPerLocality(String state, String locality);

    public String fetchStatisticsForAdherent(Long adherentId);
}
