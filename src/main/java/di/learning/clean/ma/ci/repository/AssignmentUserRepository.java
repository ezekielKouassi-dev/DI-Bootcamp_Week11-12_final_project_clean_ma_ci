package di.learning.clean.ma.ci.repository;

import di.learning.clean.ma.ci.entity.AssignmentUser;
import di.learning.clean.ma.ci.entity.AssignmentUserId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssignmentUserRepository extends JpaRepository<AssignmentUser, AssignmentUserId> {
}
