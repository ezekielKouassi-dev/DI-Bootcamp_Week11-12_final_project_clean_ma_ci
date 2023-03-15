package di.learning.clean.ma.ci.repository;

import di.learning.clean.ma.ci.entity.Adherent;
import di.learning.clean.ma.ci.entity.AssignmentUser;
import di.learning.clean.ma.ci.entity.AssignmentUserId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssignmentUserRepository extends JpaRepository<AssignmentUser, AssignmentUserId> {
    public List<AssignmentUser> findAllByAdherentAndState(Adherent adherent, String state);
    public List<AssignmentUser> findAssignmentUserByAdherent(Adherent adherent);
}
