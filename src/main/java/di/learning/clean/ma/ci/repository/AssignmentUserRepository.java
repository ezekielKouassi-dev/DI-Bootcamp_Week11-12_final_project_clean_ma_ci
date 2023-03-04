package di.learning.clean.ma.ci.repository;

import di.learning.clean.ma.ci.entity.AssignmentUser;
import di.learning.clean.ma.ci.entity.AssignmentUserId;
import di.learning.clean.ma.ci.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssignmentUserRepository extends JpaRepository<AssignmentUser, AssignmentUserId> {
    public List<AssignmentUser> findAllByUserAndAndState(User user, String state);
}
